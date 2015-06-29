Sitevision Portlet for Brightcove Videos
========================================

A portlet application built on the Spring Portlet MVC framework. Displays videos on City of Malmo's external and internal web site. The videos are retrieved from Brightcove Videocloud's REST API. 

## Dependencies
* Sitevision Server 3
* Maven 3

## Build & Deployment
* In the project's folder, run the command `mvn -P[malmo-external/malmo-komin] clean install` for example `C:\git\sv-webbvideo>mvn -Pmalmo-external clean install`. 
* Deploy manually to the server by copying the resulting file `/target/webbvideo-multiple.war` to `[Path to Sitevision server]/tomcat/webapps`. 
 
## Development
The project can be imported into Eclipse or Netbeans as a Maven project. 

## Licence
Released under AGPL version 3.
