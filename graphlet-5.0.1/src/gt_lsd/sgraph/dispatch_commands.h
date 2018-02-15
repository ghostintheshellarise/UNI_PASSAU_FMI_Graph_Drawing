/* This software is distributed under the Lesser General Public License */
/* (C) Universitaet Passau 1986-1994 */
/* GraphEd Source, 1986-1994 by Michael Himsolt	*/
#ifndef DISPATCH_COMMANDS_HEADER
#define DISPATCH_COMMANDS_HEADER

typedef	enum {
    /* Action allgemein					*/
		
    NO_ACTION,	         /* Dummy, ggf. Fehlermeldung	*/
    CREATE_MODE,	         /* Erzeuge Knoten und Kanten	*/
    EDIT_MODE,	         /* 				*/
    EDIT_SELECTION,          /* Editiere Knoten/Kante/Goup	*/
    EDIT_GRAGRA,
    EDIT_GRAPH_OF_SELECTION,
		
    CREATE_GRAPH,
    CREATE_DIRECTED_GRAPH,
    CREATE_UNDIRECTED_GRAPH,
		
    CREATE_PRODUCTION,       /* Erzeuge GraGra-Produktion	*/
    CREATE_DIRECTED_PRODUCTION,
    CREATE_UNDIRECTED_PRODUCTION,
				
    CREATE_EMBEDDING,       /* Erzeuge GraGra-Einbettung	*/
    CREATE_DIRECTED_EMBEDDING,
    CREATE_UNDIRECTED_EMBEDDING,
		
    CREATE_NODE,
    CREATE_EDGE,
				
    CREATE_BUFFER,
		
    /* SELECTION Management					*/
		
    SELECT,
    SELECT_NODE,
    SELECT_EDGE,
    SELECT_GROUP,
    SELECT_GRAPH,
    SELECT_GRAPH_OF_SELECTION,
    SELECT_ALL,
		
    EXTEND_SELECTION,
    EXTEND_SELECTION_WITH_NODE,
    EXTEND_SELECTION_WITH_EDGE,
    EXTEND_SELECTION_WITH_GROUP,
    EXTEND_SELECTION_WITH_GRAPH,
		
    UNSELECT,
				
    MOVE_SELECTION,
    RESIZE_SELECTION,	/* Nodes and groups	*/
    ROTATE_SELECTION,	/* Groups		*/
    EXTEND_EDGE,
    MOVE_EDGE,
    COMPRIME_EDGE,
		
    PUT_SELECTION,
    PUT_WHOLE_GRAPH,
    GET_SELECTION,
    GET_AS_GRAPH,
		
    ZOOM_SELECTION_DOWN,
    ZOOM_SELECTION_UP,
#define SHRINK_SELECTION ZOOM_SELECTION_DOWN
#define EXPAND_SELECTION ZOOM_SELECTION_UP
		
    SPLIT_EDGE,
    SPLIT_SELECTION,
    MERGE_SELECTION,
		
    REVERSE_EDGE,
    SWAP_SELECTED_GRAPH_DIRECTEDNESS,
    SET_GRAPH_DIRECTEDNESS,
		
    LABEL_SELECTION,
		
		/* Gragra in action					*/
		
    COMPILE_PRODUCTION,
    COMPILE_ALL_PRODUCTIONS,
    PRETTY_PRINT_PRODUCTION,
    PRETTY_PRINT_CURRENT_PRODUCTION,
    PRETTY_PRINT_ALL_PRODUCTIONS,
    SET_CURRENT_PRODUCTION,
    APPLY_CURRENT_PRODUCTION,
    APPLY_PRODUCTION,
		
    CENTER_SELECTION,
		
    SELECTION_STATISTICS,
    BUFFER_STATISTICS,
    ALL_STATISTICS,
		
    ABOUT_GRAPHED,
		
    /* Loeschen						*/
		
    DELETE_SELECTION,
    DELETE_ALL,
		
		/*							*/
		/* Defaultwerte fuer Knoten und Kanten			*/
		/*							*/
		
    NODE_DEFAULTS,
    EDGE_DEFAULTS,
		
		/* The following are mainly used for the menu		*/
		
		/* Node_edge_interface					*/
		
    SET_NEI,
    NEI_NO_NODE_EDGE_INTERFACE,	/*         Wie		*/
    NEI_TO_BORDER_OF_BOUNDING_BOX,	/* Node_edge_interface	*/
    NEI_TO_CORNER_OF_BOUNDING_BOX,	/*			*/
    NEI_CLIPPED_TO_MIDDLE_OF_NODE,	/*			*/
    NEI_SPECIAL,
		
		/* Nodelabel_placement					*/
		
    SET_NLP,
    NLP_MIDDLE,			/*         		*/
    NLP_UPPERLEFT,			/*         Wie		*/
    NLP_UPPERRIGHT,			/* Nodelabel_placement	*/
    NLP_LOWERLEFT,			/*			*/
    NLP_LOWERRIGHT,			/*			*/
		
		/* Nodesize						*/
		
    SCALE_NODESIZE,
    SCALE_NODESIZE_16_16,
    SCALE_NODESIZE_32_32,
    SCALE_NODESIZE_64_64,
    SCALE_NODESIZE_128_128,
    SCALE_NODESIZE_AS_SELECTED,
		
		/* Edgelabelsize					*/
		
    SCALE_EDGELABELSIZE,
    SCALE_EDGELABELSIZE_64_64,
    SCALE_EDGELABELSIZE_256_64,
    SCALE_EDGELABELSIZE_UNCONSTRAINED,
		
		/* Label visibility					*/
		
    SET_NODELABEL_VISIBILITY,
    SET_NODELABEL_VISIBLE,
    SET_NODELABEL_INVISIBLE,
    SET_EDGELABEL_VISIBILITY,
    SET_EDGELABEL_VISIBLE,
    SET_EDGELABEL_INVISIBLE,
		
    SET_ALL_NODELABEL_VISIBILITY,
    SET_ALL_NODELABEL_VISIBLE,
    SET_ALL_NODELABEL_INVISIBLE,
    SET_ALL_EDGELABEL_VISIBILITY,
    SET_ALL_EDGELABEL_VISIBLE,
    SET_ALL_EDGELABEL_INVISIBLE,
		
		/* Arrow length						*/
		
    SCALE_ARROWLENGTH,
    SCALE_ARROWLENGTH_0,
    SCALE_ARROWLENGTH_8,
    SCALE_ARROWLENGTH_12,
    SCALE_ARROWLENGTH_16,
    SCALE_ARROWLENGTH_AS_SELECTED,
		
		/* Arrow angle						*/
		
    SCALE_ARROWANGLE,
    SCALE_ARROWANGLE_30,
    SCALE_ARROWANGLE_45,
    SCALE_ARROWANGLE_60,
    SCALE_ARROWANGLE_AS_SELECTED,
		
    /* Gragra type						*/
		
    SET_GRAGRA_TYPE,
    SET_GRAGRA_TYPE_NCE_1,
    SET_GRAGRA_TYPE_NLC,
    SET_GRAGRA_TYPE_BNLC,
    SET_GRAGRA_TYPE_ENCE_1,

		
    /* Misc	*/
				
    SET_NODEFONT,
    SET_EDGEFONT,
    SET_NODETYPE,
    SET_EDGETYPE,
    SET_NODECOLOR,
    SET_EDGECOLOR,

    EDIT_NODETYPES,
    EDIT_EDGETYPES,
    EDIT_NODEFONTS,
    EDIT_EDGEFONTS,
		
		/* Graph laden und speichern				*/
		
    BASIC_LOAD,
    LOAD_BY_SUBFRAME,
    LOAD_AGAIN,
    LOAD_AGAIN_SILENT,
    BASIC_STORE,
    STORE_BY_SUBFRAME,
    STORE_TO_SAME_FILE,
    STORE_SILENT_TO_FILE,
		

		/* Spezial						*/
		
    REDRAW_ALL,
    SAVE_STATE,
    PRINT,
    SET_GRID,
    SET_GRID_8_8,
    SET_GRID_16_16,
    SET_GRID_32_32,
    SET_GRID_64_64,
    SET_GRID_128_128,
    SET_GRID_OFF,
    EXPAND_WORKING_AREA,
    SHRINK_WORKING_AREA,
		
    SET_CONSTRAINED,
    RESET_CONSTRAINED,
    TOGGLE_CONSTRAINED,
		
    GROUP_LABELLING_OPERATION_GOES_TO_NODE,
    GROUP_LABELLING_OPERATION_GOES_TO_EDGE,
    TOGGLE_GROUP_LABELLING_OPERATION,
		
		/* some internal routines follow	*/
		
    _CREATE_GRAPH,
		
    /* Advanced GraGra interface */

    RANDOM_APPLY_PRODUCTION,

    QUIT_GRAPHED, 	/* The final action */

		/* Final dummy						*/
		
    NUMBER_OF_WORKING_AREA_MENU_ACTIONS	/* Dummy	*/
}
User_action;



/* Command Flags */

#define MODIFIES_NOTHING 0
#define MODIFIES_SOMETHING 0x1
#define MODIFIES_GRAPH (MODIFIES_SOMETHING << 1)
#define MODIFIES_GRAPH_STRUCTURE (MODIFIES_GRAPH << 1)


extern	void		set_command_flags (User_action command, unsigned flags);
extern	unsigned	get_command_flags (User_action command);

#endif
