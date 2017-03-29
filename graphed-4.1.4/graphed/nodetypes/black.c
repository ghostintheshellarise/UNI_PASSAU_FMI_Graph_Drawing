#include "misc.h"
#include "graph.h"
#include "type.h"

#include "ps.h"
#include "repaint.h"
#include "nodetypes/nodetypes.h"


void init_black_nodetype (void)
{
  System_nodetype box;

  box = new_system_nodetype();
  box->name = "#black";
  box->adjust_func = adjust_edgeline_to_box_node;
  box->pm_paint_func = pm_paint_black_node;
  box->ps_paint_func = ps_paint_black_node;

  add_system_nodetype (box);
}


void		pm_paint_black_node (Pixmap pm, int x, int y, int w, int h, int op)
{
  XFillRectangle(display, pm, pixmap_gc,
		 x, y,
		 w, h);
}


void	ps_paint_black_node(PsPage *pspage, Node node)
{
  if(!pspage) return;

  ps_paint(pspage, PS_BOX,
	   PS_PRIM_LOCATION_XY,
	     (double)node->box.r_left,
	     (double)node->box.r_top,
	   PS_PRIM_SIZE_XY,
	     (double)node->box.r_width,
	     (double)node->box.r_height,
	   PS_PRIM_COLOR,  0.0,
	   PS_PRIM_FILLED, TRUE,
	   NULL);
}