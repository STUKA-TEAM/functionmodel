package recommend;

import java.util.List;

/**
 * @Title: LbResponse
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月10日
 */
public class LbsResponse {
    private int status;
    private String message;
    private String total;
    @SuppressWarnings("rawtypes")
    private List results;

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @return the results
     */
    @SuppressWarnings("rawtypes")
    public List getResults() {
        return results;
    }

    /**
     * @param results
     *            the results to set
     */
    @SuppressWarnings("rawtypes")
    public void setResults(List results) {
        this.results = results;
    }
}
