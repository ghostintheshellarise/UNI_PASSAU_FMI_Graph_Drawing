#ifdef UNIX5
#ifndef lint
static char version_id[] = "%Z% File : %M% %E%  %U%  Version %I% Copyright (C) 1992/93 Schweikardt Andreas";
#endif
#endif 

/*******************************************************************************
*									       *
*									       *
*			SEARCH STRATEGIES ON GRAPHS			       *
*									       *
*									       *
*	Copyright (C) 1992/93 Andreas Schweikardt			       *
********************************************************************************




	File	:	%M%

	Date	:	%E%	(%U%)

	Version	:	%I%

	Author	:	Schweikardt, Andreas



Portability

	Language		:	C
	Operating System	:	Sun-OS (UNIX)
	User Interface (graphic):	
	Other			:	GraphEd & Sgraph


********************************************************************************


Layer   : 	

Modul   :	


********************************************************************************


Description of %M% :




********************************************************************************


Functions of %M% :



*******************************************************************************/


/******************************************************************************
*                                                                             *
*			standard includes				      *
*                                                                             *
*******************************************************************************/

/******************************************************************************
*                                                                             *
*			gui includes    	 			      *
*                                                                             *
*******************************************************************************/

#include <math.h>

/******************************************************************************
*                                                                             *
*			GraphEd & Sgraph includes			      *
*                                                                             *
*******************************************************************************/

#include <sgraph/std.h>
#include <sgraph/sgraph.h>
#include <sgraph/slist.h>
#include <sgraph/graphed.h>


/******************************************************************************
*                                                                             *
*			local includes		 			      *
*                                                                             *
*******************************************************************************/

#include <search/search.h>
#include <search/algorithm.h>
#include <search/control.h>
#include <search/move.h>
#include <search/error.h>


/******************************************************************************
*                                                                             *
*			local defines 		 			      *
*                                                                             *
*******************************************************************************/

/******************************************************************************
*                                                                             *
*			local macros  		 			      *
*                                                                             *
*******************************************************************************/

/******************************************************************************
*                                                                             *
*			local types		 			      *
*                                                                             *
*******************************************************************************/

/******************************************************************************
*                                                                             *
*			local functions					      *
*                                                                             *
*******************************************************************************/

/******************************************************************************
*                                                                             *
*			global variables				      *
*                                                                             *
*******************************************************************************/

/******************************************************************************
*                                                                             *
*			local variables					      *
*                                                                             *
*******************************************************************************/

/******************************************************************************
*                                                                             *
*                                                                             *
*                       local functions		                              *
*                                                                             *
*                                                                             *
******************************************************************************/

/******************************************************************************
*                                                                             *
*                                                                             *
*                       global functions		                      *
*                                                                             *
*                                                                             *
******************************************************************************/

void	App2TreeInitAndTest(Sgraph graph, Method method)
{
	if ( COnodes() != COedges()+1 )
	{
		SetError( ERROR_NOT_A_TREE );
		/* return; *//* only useful for additional code */
	}
	return;
}



void	App2TreeSearch(Sgraph graph, Method method)
{

	unsigned int	approximation;
	unsigned int	N = COnodes();

 
        switch( method )        
        {
        case METHOD_NODE_SEARCH:  
                if ( N < 7 )   
                {
                        if ( N == 1 )
                                approximation = 1;
                        else     
                                approximation = 2;
                        
                }
                else
                        approximation = (int)(log( ((float)N + 0.5 )/2.5 )/log(3.0)) +2;
                break;  
                 
        case METHOD_EDGE_SEARCH: 
                approximation = (int)(log( (float)N -1.0 )/log(3.0)) +1;      
                break; 
                         
        case METHOD_MIXED_SEARCH: 
                approximation = (int)(log( ((float)N + 0.5)*2.0 )/log(3.0)); 
                break; 
	default:
		break;
                         
        } 
         

	COsetHiddenMaxSearchers( approximation );
	COsetMaxSearchers( approximation );

	COnotifyInfo( "Only an estimation ! (No animation)" );
	
}


/******************************************************************************
*		       [EOF] end of file %M% 
******************************************************************************/