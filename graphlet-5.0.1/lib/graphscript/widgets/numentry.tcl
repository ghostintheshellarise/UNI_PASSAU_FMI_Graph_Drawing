# This software is distributed under the Lesser General Public License
#
# widgets/numentry.tcl
#
# Numeric entry widget
#
#------------------------------------------ CVS
#
# CVS Headers -- The following headers are generated by the CVS
# version control system. Note that especially the attribute
# Author is not necessarily the author of the code.
#
# $Source: /home/br/CVS/graphlet/lib/graphscript/widgets/numentry.tcl,v $
# $Author: forster $
# $Revision: 1.5 $
# $Date: 1999/02/25 16:26:18 $
# $Locker:  $
# $State: Exp $
#
#------------------------------------------ CVS
#
# (C) University of Passau 1995-1999, Graphlet Project
#     Author: Michael Forster

package provide Numentry 1.0

namespace eval Numentry {

    namespace export create

    #
    #   +$ne-(cmd renamed to $ne.frame)---------------------+
    #   | +$ne.entry---------------------------+ +$ne.up--+ |
    #   | |                                    | |   /\   | |
    #   | |                                    | +--------+ |
    #   | |                                    | +$ne.down+ |
    #   | |                                    | |   \/   | |
    #   | +------------------------------------+ +--------+ |
    #   +---------------------------------------------------+
    #

    image create bitmap ::Numentry::up -data {
	\#define _width 8
	\#define _height 2
	static unsigned char _bits[] = { 0x18, 0x3c };
    }

    image create bitmap ::Numentry::down -data {
	\#define _width 8
	\#define _height 2
	static unsigned char _bits[] = { 0x3c, 0x18 };
    }

    Widget::create_type Numentry \
	-options {
	    -acceleration	acceleration	Acceleration	3
	    -threshold		threshold	Threshold	20
	    -delay		delay		Delay		500
	    -rate		rate		Rate		50
	    -min		min		Min		{}
	    -max		max		Max		{}
	    -step		step		Step		1
	    -default		default		Default		0
	} \
	-resources {
	    borderWidth			2
	    relief			sunken
	    highlightThickness		1
	    entry.borderWidth		0
	    entry.highlightThickness	0
	    entry.width			3
	    up.highlightThickness	0
	    up.takeFocus		0
	    up.image			::Numentry::up
	    down.highlightThickness	0	       
	    down.takeFocus		0
	    down.image			::Numentry::down
	} \
	-forward_options {
	    frame {
		highlightbackground highlightthickness relief
	    }
	    entry {
		cursor exportselection font 
		highlightbackground insertbackground insertborderwidth
		insertofftime insertontime insertwidth justify
		selectbackground selectborderwidth selectforeground
		show takefocus textvariable width xscrollcommand
	    }
	    { entry up down } { state background foreground }
	    { frame up down } { bd borderwidth }
	} \
	-forward_commands {
	    entry {
		bbox delete get icursor index insert scan selection xview
	    }
	} \
	-commands {
	    change Numentry::change
	}

    proc create { ne args } {
	
	# creation
	
	Widget::create Numentry $ne

	pack [entry $ne.entry] \
	    -side left \
	    -fill both -expand true

	pack [button $ne.up] \
	    -side top \
	    -fill y -expand true
	
	pack [button $ne.down] \
	    -side bottom \
	    -fill y -expand true
	
	# initialization
	
	eval Widget::init Numentry $ne $args

	# bindings

	bind $ne.up <ButtonPress-1> [namespace code "ev_start_inc $ne"]
	bind $ne.up <ButtonRelease-1> [namespace code "ev_stop_changing $ne"]
	
	bind $ne.down <ButtonPress-1> [namespace code "ev_start_dec $ne"]
	bind $ne.down <ButtonRelease-1> [namespace code "ev_stop_changing $ne"]

	bind $ne.entry <Up> [namespace code "$ne change +1"]
	bind $ne.entry <Down> [namespace code "$ne change -1"]

	return $ne
    }

    #================================================== Event handling

    proc ev_start_inc { ne } {
	ev_start_changing $ne [$ne cget -step]
    }

    proc ev_start_dec { ne } {
	ev_start_changing $ne [expr -[$ne cget -step]]
    }
    
    proc ev_start_changing { ne offset } {
	variable _Timer
	variable _ChangeCount

	if { [$ne cget -state] == "disabled" } { return }
	
	$ne change $offset
	set _ChangeCount 1

	set code [namespace code "ev_continue_changing $ne $offset"]
	set _Timer($ne) [after [$ne cget -delay] $code]
    }

    proc ev_continue_changing { ne offset } {
	variable _Timer
	variable _ChangeCount
	
	if { $_ChangeCount > [$ne cget -threshold] } {
	    $ne change [expr $offset * [$ne cget -acceleration]]
	} else {
	    $ne change $offset
	}
	incr _ChangeCount

	set code [namespace code "ev_continue_changing $ne $offset"]
	set _Timer($ne) [after [expr 1000/[$ne cget -rate]] $code]
    }

    proc ev_stop_changing { ne } {
	variable _Timer
	
	if { [$ne cget -state] == "disabled" } { return }

	after cancel $_Timer($ne)
    }

    proc change { ne offset } {

	upvar \#0 [$ne cget -textvariable] var

	# get value
	
	if { [info exists var] } {
	    set val $var
	} else {
	    set val [$ne get]
	}

	set newval [expr $val + $offset]
	
	# check range

	set min [$ne cget -min]
	set max [$ne cget -max]
	if { $min != {} && $newval < $min } {
	    set newval $min
	}
	if { $max != {} && $newval > $max } {
	    set newval $max
	}
	
	# set new value

	if { $val != $newval } {
	    if [info exists var] {
		set var $newval
	    } else {
		$ne.e delete 0 end
		$ne.e insert 0 $newval
	    }
	}
    }
}

#---------------------------------------------------------------------------
#   Set emacs variables
#---------------------------------------------------------------------------
# ;;; Local Variables: ***
# ;;; mode: tcl ***
# ;;; tcl-indent-level: 4 ***
# ;;; End: ***
#---------------------------------------------------------------------------
#   end of file
#---------------------------------------------------------------------------
