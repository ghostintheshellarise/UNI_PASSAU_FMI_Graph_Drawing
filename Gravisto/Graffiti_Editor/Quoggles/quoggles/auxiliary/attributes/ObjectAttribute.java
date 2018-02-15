package quoggles.auxiliary.attributes;

import java.util.ArrayList;

import org.graffiti.attributes.AbstractAttribute;
import org.graffiti.event.AttributeEvent;

/**
 * Contains a Object.
 *
 * @version $Revision: 491 $
 */
public class ObjectAttribute
    extends AbstractAttribute {
    //~ Instance fields ========================================================

    /** The value of this <code>ObjectAttribute</code>. */
    private Object value;

    //~ Constructors ===========================================================

    /**
     * Constructs a new instance of a <code>ObjectAttribute</code>.
     *
     * @param id the id of the <code>Attribute</code>.
     */
    public ObjectAttribute(String id) {
        super(id);
    }

    /**
     * Constructs a new instance of a <code>ObjectAttribute</code> with the
     * given value.
     *
     * @param id the id of the attribute.
     * @param value the value of the <code>Attribute</code>.
     */
    public ObjectAttribute(String id, Object value) {
        super(id);
        this.value = value;
    }

    //~ Methods ================================================================

    /**
     * @see org.graffiti.attributes.Attribute#setDefaultValue()
     */
    public void setDefaultValue() {
        value = new ArrayList(0);
    }

    /**
     * Sets the value of this object. The <code>ListenerManager</code> is
     * informed by the method <code>setValue()</code>.
     *
     * @param value the new value of this object.
     */
    public void setObject(Object value) {
        assert value != null;

        AttributeEvent ae = new AttributeEvent(this);
        callPreAttributeChanged(ae);
        this.value = value;
        callPostAttributeChanged(ae);
    }

    /**
     * Returns the value of this object.
     *
     * @return the value of this object.
     */
    public Object getObject() {
        return value;
    }

    /**
     * Returns the value of this attribute, i.e. contained Sting object.
     *
     * @return the value of the attribute, i.e. contained String object.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Returns a deep copy of this instance.
     *
     * @return a deep copy of this instance.
     */
    public Object copy() {
        return new ObjectAttribute(this.getId(), this.value);
    }

    /**
     * @see org.graffiti.attributes.Attribute#toString(int)
     */
    public String toString(int n) {
        return getSpaces(n) + getId() + " = \"" + value + "\"";
    }

    /**
     * Sets the value of the <code>Attribute</code>. The
     * <code>ListenerManager</code> is informed by the method
     * <code>setValue()</code>.
     *
     * @param o the new value of the attribute.
     *
     * @exception IllegalArgumentException if the parameter has not the
     *            appropriate class for this <code>Attribute</code>.
     */
    protected void doSetValue(Object o)
        throws IllegalArgumentException {
        assert o != null;

        try {
            value = o;
        } catch(ClassCastException cce) {
            throw new IllegalArgumentException("Invalid value type.");
        }
    }

    /**
     * @see org.graffiti.plugin.Displayable#toXMLString()
     */
    public String toXMLString() {
        return getStandardXML(value.toString());
    }
}

//------------------------------------------------------------------------------
//   end of file
//------------------------------------------------------------------------------
