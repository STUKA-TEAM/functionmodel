package recommend;

import java.util.Map;

/**
 * @Title: Store
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月7日
 */
public class Store {
    private String name;
    private Map<String, String> location;
    private String address;
    private String street_id;
    private String telephone;
    private String uid;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the location
     */
    public Map<String, String> getLocation() {
        return location;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation(Map<String, String> location) {
        this.location = location;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the street_id
     */
    public String getStreet_id() {
        return street_id;
    }

    /**
     * @param street_id
     *            the street_id to set
     */
    public void setStreet_id(String street_id) {
        this.street_id = street_id;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     *            the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }
}
