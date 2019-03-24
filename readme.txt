# Instructions to Install

---------------------- Postgres on linux -------------------------
[sudo su postgres] (Log in postgres)
# Enter in mode SQL:
[psql]
# Show databases:
[\l]
# Show users
[\du]
[alter user postgres with password 'admin';]
[create user hugoiguana with password 'h33644';]
[alter user hugoiguana with superuser;]
[\du]
[drop user hugoiguana;]
[create database geanuncio;]
[\q] (Sai do sql)
[man psql] (Exibe ajuda)
# Open pgadmin and config a new conection with the data below:
---------------
name: geanuncio
Host: 127.0.0.1
Port: 5432
Username: hugoiguana
Senha: h33644
---------------
-------------------------Lombok on Intellij-----------------------
With Intellij opened go to "File -> Settings -> Plugins", search for "lombok", install it and restart the intellij.
------------------------------------------------------------------
-----------------------Start Application-------------------------
Start the class Geanuncion1Application.java and in the browser call:
http://localhost:8080/ad/list
If everythings goes rigth, you will see a json list with the all Ads preinstalled.
------------------------------------------------------------------
------------------------------------------------------------------
------------------------------------------------------------------
------------------------------------------------------------------
------------------------------------------------------------------
------------------------------------------------------------------
------------------------------------------------------------------