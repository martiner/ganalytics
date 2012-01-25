package cz.geek.googleanalytics.taglib;

import org.apache.commons.lang.Validate;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

/**
 * Creates a transaction object with the given values. As with _addItem(), this method handles only transaction tracking
 * and provides no additional ecommerce functionality. Therefore, if the transaction is a duplicate of an existing
 * transaction for that session, the old transaction values are over-written with the new transaction values.
 * http://code.google.com/apis/analytics/docs/gaJS/gaJSApiEcommerce.html#_gat.GA_Tracker_._addTrans
 */
public class AddTransTag extends AbstractTag {

	private String orderId;

	private String affiliation;

	private String total;

	private String tax;

	private String shipping;

	private String city;

	private String state;

	private String country;

	@Override
	public int doEndTag() throws JspException {
		Validate.notEmpty(orderId, "orderId can not be empty");
		Validate.notEmpty(total, "total can not be empty");
		getGaq().add("_addTrans", orderId, affiliation, total, tax, shipping, city, state, country);
		return Tag.EVAL_PAGE;
	}

	/**
	 * Internal unique order id number for this transaction.
	 * @param orderId order id
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * Partner or store affiliation
	 * @param affiliation affiliation
	 */
	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	/**
	 * Total amount of the transaction.
	 * @param total total
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * Tax amount of the transaction.
	 * @param tax tax
	 */
	public void setTax(String tax) {
		this.tax = tax;
	}

	/**
	 * Shipping charge for the transaction.
	 * @param shipping shipping
	 */
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	/**
	 * City to associate with transaction.
	 * @param city city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * State to associate with transaction.
	 * @param state state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Country to associate with transaction.
	 * @param country coutry
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public String getTotal() {
		return total;
	}

	public String getTax() {
		return tax;
	}

	public String getShipping() {
		return shipping;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}
}
