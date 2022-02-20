  Cls

@ECHO OFF

Title TESTFOLDER

If EXIST "Control Panel.{21EC2020-3AEA-1069-A2DD-08002B30309D}" goto UNLOCK

If NOT EXIST TESTFOLDER goto MDTESTFOLDER

:CONFIRM

Echo Are you sure u want to Lock the folder(Y/N)

Set/p "cho=>"

If %cho%==Y goto LOCK

If %cho%==y goto LOCK

If %cho%==n goto END

If %cho%==N goto END

Echo Invalid choice.

Goto CONFIRM

:LOCK

Ren TESTFOLDER "Control Panel.{21EC2020-3AEA-1069-A2DD-08002B30309D}"

Attrib +h +s "Control Panel.{21EC2020-3AEA-1069-A2DD-08002B30309D}"

Echo Folder locked

Goto End

:UNLOCK

Echo Enter password to Unlock folder

Set/p "pass=>"

Rem default password is 1234

If NOT %pass%==1234 goto FAIL

Attrib -h -s "Control Panel.{21EC2020-3AEA-1069-A2DD-08002B30309D}"

Ren "Control Panel.{21EC2020-3AEA-1069-A2DD-08002B30309D}" TESTFOLDER

Echo Folder Unlocked successfully

Goto End

:FAIL

Echo Invalid password

Goto end

Rem creates a folder called TESTFOLDER in the working dir if it does not exist

:MDTESTFOLDER

Md TESTFOLDER

Echo TESTFOLDER created successfully

pause

Goto End

:End

pause