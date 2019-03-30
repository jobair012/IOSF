package nu.ist.iosf.commons.models.appBasic;

import java.util.List;

public class IOSFSearchResult {

    private Long total = Long.valueOf(0);
    private List rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
