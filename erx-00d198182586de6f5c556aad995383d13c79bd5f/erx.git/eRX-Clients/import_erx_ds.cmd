call C:/IBM/WAS_ND_8502/AppServer/bin/setupCmdLine.bat
set CURRENTPATH=%PATH%
set PATH=C:/IBM/WAS_ND_8502/AppServer/bin/
set servurl=http://localhost:9083
set wsdluri=erxds/serv/ErxDecisionServices.wsdl
wsimport -verbose -keep -s ./src -d ./bin  %servurl%/%wsdluri%
set PATH=%CURRENTPATH%
