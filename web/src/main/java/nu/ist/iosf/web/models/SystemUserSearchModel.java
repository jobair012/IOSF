package nu.ist.iosf.web.models;

import nu.ist.iosf.commons.models.request.IOSFSearchModel;
import nu.ist.iosf.commons.models.response.SystemUserSearchData;

public class SystemUserSearchModel extends IOSFSearchModel {

    private String userId;
    private String fullName;
    private String role;
    private String status;
    private String fromDate;
    private String toDate;

    private SystemUserSearchData searchData;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public SystemUserSearchData getSearchData() {
        return searchData;
    }

    public void setSearchData(SystemUserSearchData searchData) {
        this.searchData = searchData;
    }
}
