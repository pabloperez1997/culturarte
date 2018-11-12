mkdir build
javac -encoding UTF-8 -cp ".\lib\mysql-connector-java-5.1.46-bin.jar;.\lib\commons-io-2.6.jar;.\lib\jcalendar-1.4.jar;.\lib\RSFoto_v1.0.jar;.\lib\gson-2.8.4.jar;.\lib\AbsoluteLayout.jar;" -d .\build .\src\DtServicios\*.java .\src\logica\*.java .\src\logica\Interfaces\*.java .\src\logica\Controladores\*.java .\src\logica\Clases\*.java  .\src\Persistencia\*.java .\src\Presentacion\*.java .\src\Servicios\*.java
jar cvfm .\culturarte.jar .\src\manifest.mf -C .\build . > temp.txt
pause


