package nu.ist.iosf.commons.models.request;

public class IOSFLookupRequest {

    private String lookupKey;

    public IOSFLookupRequest() {
    }

    public IOSFLookupRequest(String lookupKey) {
        this.lookupKey = lookupKey;
    }

    public String getLookupKey() {
        return lookupKey;
    }

    public void setLookupKey(String lookupKey) {
        this.lookupKey = lookupKey;
    }
}
