package cz.fi.muni.pa165.dogbarber.mvc.exceptions;

/**
 *
 * @author Severin Simko
 */
public class CustomGenericException extends RuntimeException {
	private static final long serialVersionUID = -1116361905230645321L;
	
	private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public CustomGenericException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

}
