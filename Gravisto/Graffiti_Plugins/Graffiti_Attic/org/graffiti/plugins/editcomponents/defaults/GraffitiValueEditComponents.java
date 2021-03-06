// =============================================================================
//
//   GraffitiValueEditComponents.java
//
//   Copyright (c) 2001-2006 Gravisto Team, University of Passau
//
// =============================================================================
// $Id: GraffitiValueEditComponents.java 5772 2010-05-07 18:47:22Z gleissner $

package org.graffiti.plugins.editcomponents.defaults;

import org.graffiti.attributes.BooleanAttribute;
import org.graffiti.attributes.ByteAttribute;
import org.graffiti.attributes.DoubleAttribute;
import org.graffiti.attributes.EdgeShapeAttribute;
import org.graffiti.attributes.FloatAttribute;
import org.graffiti.attributes.IntegerAttribute;
import org.graffiti.attributes.LongAttribute;
import org.graffiti.attributes.NodeShapeAttribute;
import org.graffiti.attributes.ShortAttribute;
import org.graffiti.attributes.StringAttribute;
import org.graffiti.graphics.ColorAttribute;
import org.graffiti.graphics.LineModeAttribute;
import org.graffiti.plugin.EditorPluginAdapter;
import org.graffiti.plugin.parameter.BooleanParameter;
import org.graffiti.plugin.parameter.DoubleParameter;
import org.graffiti.plugin.parameter.FloatParameter;
import org.graffiti.plugin.parameter.IntegerParameter;
import org.graffiti.plugin.parameter.ProbabilityParameter;
import org.graffiti.plugin.parameter.StringParameter;

/**
 * This class is a plugin providing some default value edit components.
 * 
 * @version $Revision: 5772 $
 * 
 * @see org.graffiti.plugin.editcomponent.ValueEditComponent
 * @see org.graffiti.plugin.GenericPlugin
 */
public class GraffitiValueEditComponents extends EditorPluginAdapter {

    // /**
    // * The number of edit components this plugin provides.
    // */
    // private static final int NUMBER_OF_VECS = 20;

    /**
     * Constructs a new <code>GraffitiValueEditComponent</code>.
     */
    public GraffitiValueEditComponents() {
        super();

        // register the ui compoents for the displayable types
        valueEditComponents.put(ColorAttribute.class,
                ColorChooserEditComponent.class);
        valueEditComponents.put(LineModeAttribute.class,
                LineModeEditComponent.class);
        valueEditComponents.put(IntegerAttribute.class,
                IntegerEditComponent.class);
        valueEditComponents.put(ShortAttribute.class, ShortEditComponent.class);
        valueEditComponents.put(LongAttribute.class, LongEditComponent.class);
        valueEditComponents.put(ByteAttribute.class, ByteEditComponent.class);
        valueEditComponents.put(FloatAttribute.class, FloatEditComponent.class);
        valueEditComponents.put(DoubleAttribute.class,
                DoubleEditComponent.class);
        valueEditComponents.put(BooleanAttribute.class,
                BooleanEditComponent.class);
        valueEditComponents.put(NodeShapeAttribute.class,
                NodeShapeEditComponent.class);
        valueEditComponents.put(EdgeShapeAttribute.class,
                EdgeShapeEditComponent.class);
        valueEditComponents.put(StringAttribute.class,
                StringEditComponent.class);

        // register the ui compoents for the parameter types
        valueEditComponents.put(IntegerParameter.class,
                IntegerEditComponent.class);
        valueEditComponents.put(DoubleParameter.class,
                DoubleEditComponent.class);
        valueEditComponents.put(ProbabilityParameter.class,
                DoubleEditComponent.class);
        valueEditComponents.put(FloatParameter.class, FloatEditComponent.class);
        valueEditComponents.put(StringParameter.class,
                StringEditComponent.class);
        valueEditComponents.put(BooleanParameter.class,
                BooleanEditComponent.class);
    }
}

// -----------------------------------------------------------------------------
// end of file
// -----------------------------------------------------------------------------
