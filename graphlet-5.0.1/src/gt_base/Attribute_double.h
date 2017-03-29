/* This software is distributed under the Lesser General Public License */
#ifndef GT_ATTRIBUTE_DOUBLE_H
#define GT_ATTRIBUTE_DOUBLE_H

//
// Attribute_double.h
//
// This file defines the class GT_Attribute_double.
//
//------------------------------------------ CVS
//
// CVS Headers -- The following headers are generated by the CVS
// version control system. Note that especially the attribute
// Author is not necessarily the author of the code.
//
// $source: /home/br/CVS/graphlet/src/gt_base/Attributes.h,v $
// $Author: himsolt $
// $Revision: 1.2 $
// $Date: 1999/03/05 20:42:57 $
// $Locker:  $
// $State: Exp $
//
//------------------------------------------ CVS
//
// (C) University of Passau 1995-1999, Graphlet Project
//


#ifndef GT_ATTRIBUTE_BASE_H
#include "Attribute_Base.h"
#endif


//////////////////////////////////////////
//
// GT_Attribute_double
//
//////////////////////////////////////////


class GT_Attribute_double : public GT_Attribute_Base {

    GT_CLASS (GT_Attribute_double, GT_Attribute_Base);
    
protected:
    double the_value;
public:

    GT_Attribute_double (const GT_Key key, const double& i,
	const int flags = 0);
    virtual ~GT_Attribute_double();

    // Value
    inline void value (const double v);    
    inline const double value() const;
    inline double& value();

    // Value extraction
    virtual bool value_double (double& i) const;

    // Printing
    virtual void print (ostream &out) const;
    
    // Cloning: virtual copy constructor
    void copy (const GT_Attribute_double* from, GT_Copy type);
    virtual GT_Attribute_Base* clone (GT_Copy type) const;
};



inline void GT_Attribute_double::value (const double v)
{
    the_value = v;
}


inline const double GT_Attribute_double::value() const
{
    return the_value;
}


inline double& GT_Attribute_double::value()
{
    return the_value;
}

#endif