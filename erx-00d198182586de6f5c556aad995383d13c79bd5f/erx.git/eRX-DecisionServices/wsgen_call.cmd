@echo on
call C:/IBM/WAS_ND_8502/AppServer/bin/setupCmdLine.bat
set CURRENTPATH=%PATH%
set PATH=C:/IBM/WAS_ND_8502/AppServer/bin/;
set webcontent=./WebContent

set wsdl_dir=%webcontent%/WEB-INF/wsdl
set build_dir=%webcontent%/WEB-INF/classes
set repo=%webcontent%/WEB-INF/lib
set cp=%build_dir%;%repo%/eRX-Model-0.2.0.jar;%build_dir%;
call wsgen.bat -cp %cp% -wsdl -r %wsdl_dir%  -d %build_dir% com.walgreens.pharmacy.ds.ErxDecisionServiceImpl
set PATH=%CURRENTPATH%