@echo off

call :setEnvironment

goto :eof

:: 
:: The purpose of setEnvironment is to ensure that correct versions of JDK and ANT are used.
:: Script loads and sets environment variables from custom.properties file.
:: To switch to other JDK or ANT version simply change custom.properties. 
::
:setEnvironment

:: Clear system wide classpath because it may affect build process
set CLASSPATH=

::set verbose=false
set CURRENT_SCRIPT_DIR=%~dp0.

call :load-properties-from-file %CURRENT_SCRIPT_DIR%/build.properties
::call :load-properties-from-file %CURRENT_SCRIPT_DIR%/custom.properties

call :check-required-variable jdkHome %jdkHome%

call :check-required-file "%jdkHome%/bin/java.exe" "Java SDK not found at '%jdkHome%'! Check variable 'jdkHome'."

set JAVA_HOME=%jdkHome%

goto :eof


:: =============================================================================
:: load environment variables from properties file
:: call :load-properties-from-file <properties-file>
:: =============================================================================
:load-properties-from-file
set file=%1

if NOT EXIST %file% goto :eof

for /F "tokens=*" %%l in (%file%) do (
    call :process-property-line %%l
)
goto :eof

:process-property-line
set line=%*

:: Get first char
set firstChar=%line:~0,1%

:: Skip comment
if "%firstChar%"=="#" goto :eof

for /F "delims== tokens=1,2" %%i in ("%line%") do (
    set %%i=%%j
)

goto :eof

:check-required-variable
if "%2"=="" call :exit "Error! Environment variable '%1' MUST be explicitly set! Use custom.properties file."
goto :eof

:check-required-file
if NOT EXIST %1 call :exit %2
goto :eof


:exit
set message=%1
echo %message%
exit /B
goto :eof
