#include "misc.h"
#include "graph.h"
#include "type.h"
#include "adjust.h"

#include "ps.h"
#include "repaint.h"
#include "nodetypes/nodetypes.h"



void init_box_nodetype (void)
{
  System_nodetype box;

  box = new_system_nodetype();
  box->name = "#box";
  box->adjust_func = adjust_edgeline_to_box_node;
  box->pm_paint_func = pm_paint_box_node;
  box->ps_paint_func = ps_paint_box_node;

  add_system_nodetype (box);
}


void		pm_paint_box_node (Pixmap pm, int x, int y, int w, int h, int op)
{
	register int	x1,y1, x2,y2;
	
	x1 = x;
	y1 = y;
	x2 = x + w-1;
	y2 = y + h-1;

	XDrawLine(display,pm,pixmap_gc,x1,y1,x1,y2);
	XDrawLine(display,pm,pixmap_gc,x1,y2,x2,y2);
	XDrawLine(display,pm,pixmap_gc,x2,y2,x2,y1);
	XDrawLine(display,pm,pixmap_gc,x2,y1,x1,y1);
}


void	adjust_edgeline_to_box_node (Node node, int *x1, int *y1, int x2, int y2)
{
  clip_line_out_of_node (node, x1,y1, x2,y2);
}


void	ps_paint_box_node(PsPage *pspage, Node node)
{
  if(!pspage) return;

  ps_paint(pspage, PS_BOX,
	   PS_PRIM_LOCATION_XY,
	   (double)node->box.r_left,
	   (double)node->box.r_top,
	   PS_PRIM_SIZE_XY,
	   (double)node->box.r_width,
	   (double)node->box.r_height,
	   NULL);
}

