package com.ep.zookeeper.test;

public class DcModle {
	
	private SYS_HEAD SYS_HEAD;
	
	private SRV_HEAD SRV_HEAD;
	
	private SRV_BODY SRV_BODY;

	public SYS_HEAD getSYS_HEAD() {
		return SYS_HEAD;
	}

	public void setSYS_HEAD(SYS_HEAD sYS_HEAD) {
		SYS_HEAD = sYS_HEAD;
	}

	public SRV_HEAD getSRV_HEAD() {
		return SRV_HEAD;
	}

	public void setSRV_HEAD(SRV_HEAD sRV_HEAD) {
		SRV_HEAD = sRV_HEAD;
	}

	public SRV_BODY getSRV_BODY() {
		return SRV_BODY;
	}

	public void setSRV_BODY(SRV_BODY sRV_BODY) {
		SRV_BODY = sRV_BODY;
	}

	public static class SYS_HEAD{
		private String PINBLOCK;

		public String getPINBLOCK() {
			return PINBLOCK;
		}

		public void setPINBLOCK(String pINBLOCK) {
			PINBLOCK = pINBLOCK;
		}
	}
	
	public static class SRV_HEAD{
		private String TXN_COD;

		public String getTXN_COD() {
			return TXN_COD;
		}

		public void setTXN_COD(String tXN_COD) {
			TXN_COD = tXN_COD;
		}
	}
	
	public static class SRV_BODY{
		
		private String ORPRA_BFLG;
		private FDC0011 FDC0011;
		private String BANK_KIND;

		public static class FDC0011{
			private String DIS_LN_COD;

			public String getDIS_LN_COD() {
				return DIS_LN_COD;
			}

			public void setDIS_LN_COD(String dIS_LN_COD) {
				DIS_LN_COD = dIS_LN_COD;
			}
		}
		
		public String getORPRA_BFLG() {
			return ORPRA_BFLG;
		}

		public void setORPRA_BFLG(String oRPRA_BFLG) {
			ORPRA_BFLG = oRPRA_BFLG;
		}


		public FDC0011 getFDC0011() {
			return FDC0011;
		}

		public void setFDC0011(FDC0011 fDC0011) {
			FDC0011 = fDC0011;
		}

		public String getBANK_KIND() {
			return BANK_KIND;
		}

		public void setBANK_KIND(String bANK_KIND) {
			BANK_KIND = bANK_KIND;
		}
	}

}
