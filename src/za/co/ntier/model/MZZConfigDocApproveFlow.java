package za.co.ntier.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MTable;
import org.compiere.util.CCache;
import org.compiere.util.Env;

import za.co.ntier.api.model.I_ZZConfigDocApproveFlow;
import za.co.ntier.api.model.X_ZZConfigDocApproveFlow;

public class MZZConfigDocApproveFlow extends X_ZZConfigDocApproveFlow{
	
	public MZZConfigDocApproveFlow(Properties ctx, int ZZConfigDocApproveFlowID, String trxName,
			String[] virtualColumns) {
		super(ctx, ZZConfigDocApproveFlowID, trxName, virtualColumns);
	}
	public MZZConfigDocApproveFlow(Properties ctx, int ZZConfigDocApproveFlowID, String trxName) {
		super(ctx, ZZConfigDocApproveFlowID, trxName);
	}
	
	public MZZConfigDocApproveFlow(Properties ctx, String ZZConfigDocApproveFlowUU, String trxName) {
		super(ctx, ZZConfigDocApproveFlowUU, trxName);
	}

	public MZZConfigDocApproveFlow(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MZZConfigDocApproveFlow(Properties ctx, String ZZConfigDocApproveFlowUU, String trxName,
			String[] virtualColumns) {
		super(ctx, ZZConfigDocApproveFlowUU, trxName, virtualColumns);
	}

	private static final long serialVersionUID = -3734617421720774891L;

	

	private static final CCache<String, X_ZZConfigDocApproveFlow> cache = new CCache<>("X_ZZConfigDocApproveFlow", 10);

    public static X_ZZConfigDocApproveFlow getByDocTableID(int docADTableID) {
        // Check cache first
    	int clientID = Env.getAD_Client_ID(Env.getCtx());
    	String cacheKey = String.format("%s-%s", clientID, docADTableID);
        X_ZZConfigDocApproveFlow configDocApproveFlow = cache.get(cacheKey);
        if (configDocApproveFlow != null) {
            return configDocApproveFlow;
        }

        // Fetch from database if not in cache
        configDocApproveFlow = (X_ZZConfigDocApproveFlow)MTable.get(I_ZZConfigDocApproveFlow.Table_ID).getPO("AD_Table_ID = ?", new Object [] {docADTableID}, null);

        // Add to cache
        if (configDocApproveFlow != null) {
            cache.put(cacheKey, configDocApproveFlow);
        }

        return configDocApproveFlow;
    }
}
