package za.co.ntier.fa.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_A_Asset_Change;
import org.compiere.model.MAssetChange;
import org.compiere.model.X_A_Asset_Change;
import org.compiere.util.DB;
import org.compiere.util.Env;

@org.adempiere.base.Model(table="A_Asset_Change")
public class MZZAssetChange extends MAssetChange {
	private static final long serialVersionUID = -6101062919466408893L;

	public MZZAssetChange(Properties ctx, int assetChangeID, String trxName) {
		super(ctx, assetChangeID, trxName);
	}

	public MZZAssetChange(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MZZAssetChange(Properties ctx, String assetChangeUU, String trxName) {
		super(ctx, assetChangeUU, trxName);
	}

	public static MAssetChange createAssetChangeForRevaluation(Properties ctx, String trxName, boolean isCostChange, int assetId,
			String postingType, int acctSchemaId, BigDecimal accumDepreciationAmt, Timestamp dateAcct) {
		MAssetChange change = new MAssetChange(ctx, 0, trxName);
		change.setChangeType(isCostChange?X_A_Asset_Change.CHANGETYPE_Revaluation:X_A_Asset_Change.CHANGETYPE_Usage);
		change.setA_Asset_ID(assetId);
		change.setPostingType(postingType);
		change.setC_AcctSchema_ID(acctSchemaId);
		change.setAssetAccumDepreciationAmt(accumDepreciationAmt);
		change.setDateAcct(dateAcct);
		return change;
	}

	public static List<MAssetChange> getAssetChangesForRevaluation(int assetID, String postingType, int acctSchemaID, Timestamp lastDateDepreciated, String trxName) {
		String sqlQueryAssetChange = String.format("""
				WITH latest AS (
				    SELECT DISTINCT ON (%s) *
				    FROM %s
				    WHERE
				        %s = ?
				        AND %s IN (?, ?)
				        AND %s = ?
				        AND %s = ?
				        AND %s >= ?
				    ORDER BY %s, %s DESC)
				SELECT *
				FROM latest
				ORDER BY %s DESC;
				""",
				I_A_Asset_Change.COLUMNNAME_ChangeType,
				I_A_Asset_Change.Table_Name,
				I_A_Asset_Change.COLUMNNAME_A_Asset_ID,
				I_A_Asset_Change.COLUMNNAME_ChangeType,
				I_A_Asset_Change.COLUMNNAME_C_AcctSchema_ID,
				I_A_Asset_Change.COLUMNNAME_PostingType,
				I_A_Asset_Change.COLUMNNAME_DateAcct,
				I_A_Asset_Change.COLUMNNAME_ChangeType,
				I_A_Asset_Change.COLUMNNAME_DateAcct,
				I_A_Asset_Change.COLUMNNAME_DateAcct
				);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MAssetChange> result = new ArrayList<>();
		try
		{
			pstmt = DB.prepareStatement(sqlQueryAssetChange, trxName);
			DB.setParameters(pstmt, new Object [] {assetID, X_A_Asset_Change.CHANGETYPE_Revaluation, X_A_Asset_Change.CHANGETYPE_Usage, acctSchemaID, postingType, lastDateDepreciated});
			rs = pstmt.executeQuery();
			while (rs.next()){
				MAssetChange assetChange = new MAssetChange(Env.getCtx(), rs, trxName);
				result.add(assetChange);
			}

			return result;
		}
		catch (Exception e){
			throw new AdempiereException(e);
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		/*return new Query(Env.getCtx(), I_A_Asset_Change.Table_Name, whereClause, trxName)
									.setParameters()
									.setOrderBy(I_A_Asset_Change.COLUMNNAME_DateAcct + " DESC")
									.list();*/
	}
}
