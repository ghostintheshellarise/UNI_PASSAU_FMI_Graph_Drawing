# Microsoft Developer Studio Project File - Name="gt_lsd_gem" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 5.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Static Library" 0x0104

CFG=gt_lsd_gem - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "gt_lsd_gem.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "gt_lsd_gem.mak" CFG="gt_lsd_gem - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "gt_lsd_gem - Win32 Release" (based on "Win32 (x86) Static Library")
!MESSAGE "gt_lsd_gem - Win32 Debug" (based on "Win32 (x86) Static Library")
!MESSAGE 

# Begin Project
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe

!IF  "$(CFG)" == "gt_lsd_gem - Win32 Release"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 0
# PROP BASE Output_Dir "Release"
# PROP BASE Intermediate_Dir "Release"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 0
# PROP Output_Dir "Release"
# PROP Intermediate_Dir "Release"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_WINDOWS" /YX /FD /c
# ADD CPP /nologo /W3 /GR- /GX /O2 /I "..\..\src\sgraph" /I "..\..\..\GTL\include" /I "..\..\src" /I "e:\Programs\Tcl\include" /I "..\..\src\gt_lsd" /D "NDEBUG" /D "WIN32" /D "_WINDOWS" /D "_LSD" /YX /FD /c
# SUBTRACT CPP /Fr
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LIB32=link.exe -lib
# ADD BASE LIB32 /nologo
# ADD LIB32 /nologo /out:"..\Release\gt_lsd_gem.lib"

!ELSEIF  "$(CFG)" == "gt_lsd_gem - Win32 Debug"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 1
# PROP BASE Output_Dir "Debug"
# PROP BASE Intermediate_Dir "Debug"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 1
# PROP Output_Dir "Debug"
# PROP Intermediate_Dir "Debug"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /GX /Z7 /Od /D "WIN32" /D "_DEBUG" /D "_WINDOWS" /YX /FD /c
# ADD CPP /nologo /W3 /GR- /GX /Z7 /Od /I "..\..\..\GTL\include" /I "..\..\src" /I "e:\Programs\Tcl\include" /I "..\..\src\gt_lsd" /D "_DEBUG" /D "WIN32" /D "_WINDOWS" /D "_LSD" /YX /FD /c
# SUBTRACT CPP /X /Fr
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LIB32=link.exe -lib
# ADD BASE LIB32 /nologo
# ADD LIB32 /nologo /out:"..\Debug\gt_lsd_gem.lib"

!ENDIF 

# Begin Target

# Name "gt_lsd_gem - Win32 Release"
# Name "gt_lsd_gem - Win32 Debug"
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\adtgraph.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\adtgraph.h
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\embedder.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\embedder.h
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\gem_export.h
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\gem_main.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\gem_main.h
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\geometry.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\geometry.h
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\global.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\global.h
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\gsearch.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\gsearch.h
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\gt_lsd_gem.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\gt_lsd_gem.h
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\interface.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\interface.h
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\lsd_gem_panel.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\quality.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\gt_lsd_gem\quality.h
# End Source File
# End Target
# End Project