# Borrower-master
Calculate a repayment plan for an annuity loan

Its spring boot application which expose 3 REST endpoint and being used to calculate a repayment plan specific input parameters are necessary:
• duration (number of instalments in months)
• nominal interest rate
• total loan amount ("total principal amount")
• Date of Disbursement/Payout

Setup the project locally:
1) First import the maven project.
   Update the project, maven clean and install 
2) Since i am using lombak libary, 
   2.1) For eclipse user: please add in eclipse folder and add the line in eclipse.ini file 
   -javaagent:/Users/neel/Documents/lib/lombok-1.16.10.jar 
   For reference please find the eclipse.ini file:
   
/* -startup ../Eclipse/plugins/org.eclipse.equinox.launcher_1.5.300.v20190213-1655.jar 
--launcher.library /Users/neel/.p2/pool/plugins/org.eclipse.equinox.launcher.cocoa.macosx.x86_64_1.1.1000.v20190125-2016 
-product org.eclipse.epp.package.java.product 
-showsplash org.eclipse.epp.package.common 
--launcher.defaultAction openFile 
--launcher.appendVmargs 
-vm /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/bin 
-vmargs 
-Dosgi.requiredJavaVersion=1.8 
-Dosgi.instance.area.default=@user.home/eclipse-workspace 
-XX:+UseG1GC -XX:+UseStringDeduplication 
--add-modules=ALL-SYSTEM 
-XstartOnFirstThread 
-Dorg.eclipse.swt.internal.carbon.smallFonts 
-Dosgi.requiredJavaVersion=1.8 
-javaagent:/Users/neel/Documents/lib/lombok-1.16.10.jar 
-Dosgi.dataAreaRequiresExplicitInit=true -Xms256m -Xmx1024m 
--add-modules=ALL-SYSTEM -Xdock:icon=../Resources/Eclipse.icns 
-XstartOnFirstThread -Dorg.eclipse.swt.internal.carbon.smallFonts 
-Declipse.p2.max.threads=10 
-Doomph.update.url=http://download.eclipse.org/oomph/updates/milestone/latest 
-Doomph.redirection.index.redirection=index:/->http://git.eclipse.org/c/oomph/org.eclipse.oomph.git/plain/setups/
*/

  2.2) For Intellij users: Add the lombak libary directly in IDE.

3) To deploy the application: 
   3.1) by docker file: 
       3.1.1) Build: docker build --file=DockerFile --tag=borrower-service:latest --rm=true . 
       3.1.2) Run: docker run --name=borrower-service --publish=8081:8081 borrower-service:latest 
   3.2) by docker compose file: 
       3.2.1) docker-compose up

Hit the swagger url API exposed on 8081 port number, below is the swagger ui url for this API: http://localhost:8081/swagger-ui.html

Input: In JSON format

{
"loanAmount": "5000",
"nominalRate": "5.0",
"duration": 24,
"startDate": "2018-01-01T00:00:01Z"
}


Output:
{
  "borrowerPlan": [
    {
      "borrowerPaymentAmount": 219.36,
      "date": "2018-01-01T00:00:01.000Z",
      "initialOutstandingPrincipal": 5000,
      "interest": 20.83,
      "principal": 198.53,
      "remainingOutstandingPrincipal": 4801.47
    },
    {
      "borrowerPaymentAmount": 219.36,
      "date": "2018-01-01T00:00:01.000Z",
      "initialOutstandingPrincipal": 4801.47,
      "interest": 20.01,
      "principal": 199.35,
      "remainingOutstandingPrincipal": 4602.12
    },
    {
      "borrowerPaymentAmount": 219.36,
      "date": "2018-01-01T00:00:01.000Z",
      "initialOutstandingPrincipal": 4602.12,
      "interest": 19.18,
      "principal": 200.18,
      "remainingOutstandingPrincipal": 4401.94
    },
.....

  }
 ]
}
