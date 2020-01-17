package regressionexcellcreator.objects;

public class Cases
{
    private String errorStackTrace;

    private String stdout;

    private String className;

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    private String featureName;
    private String specName;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getSuitName() {
        return suitName;
    }

    public void setSuitName(String suitName) {
        this.suitName = suitName;
    }

    private String deviceType;
    private String suitName;




    private String stderr;

    private String skipped;

    private String duration;

    private String[] testActions;

    private String name;

    private String failedSince;

    private String skippedMessage;

    private String age;

    private String errorDetails;

    private String status;

    private String failureAnalysis;

    private String failureType;

    public String getCbId() {
        return cbId;
    }

    public void setCbId(String cbId) {
        this.cbId = cbId;
    }

    public String getFailureAnalysis() {
        return failureAnalysis;
    }

    public String getFailureType() {
        return failureType;
    }

    public void setFailureType(String failureType) {
        this.failureType = failureType;
    }
    public void setFailureAnalysis(String failureAnalysis) {
        this.failureAnalysis = failureAnalysis;
    }

    public String getScreenShotLocation() {
        return screenShotLocation;
    }

    public void setScreenShotLocation(String screenShotLocation) {
        this.screenShotLocation = screenShotLocation;
    }

    private String cbId;

    private String screenShotLocation;



    public int getIterationFailed() {
        return iterationFailed;
    }

    public void setIterationFailed(int iterationFailed) {
        this.iterationFailed = iterationFailed;
    }

    private int iterationFailed;

    public String getErrorStackTrace ()
    {
        return errorStackTrace;
    }

    public void setErrorStackTrace (String errorStackTrace)
    {
        this.errorStackTrace = errorStackTrace;
    }

    public String getStdout ()
    {
        return stdout;
    }

    public void setStdout (String stdout)
    {
        this.stdout = stdout;
    }

    public String getClassName ()
    {
        return className;
    }

    public void setClassName (String className)
    {
        this.className = className;
    }

    public String getStderr ()
    {
        return stderr;
    }

    public void setStderr (String stderr)
    {
        this.stderr = stderr;
    }

    public String getSkipped ()
    {
        return skipped;
    }

    public void setSkipped (String skipped)
    {
        this.skipped = skipped;
    }

    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
    }

    public String[] getTestActions ()
    {
        return testActions;
    }

    public void setTestActions (String[] testActions)
    {
        this.testActions = testActions;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getFailedSince ()
    {
        return failedSince;
    }

    public void setFailedSince (String failedSince)
    {
        this.failedSince = failedSince;
    }

    public String getSkippedMessage ()
    {
        return skippedMessage;
    }

    public void setSkippedMessage (String skippedMessage)
    {
        this.skippedMessage = skippedMessage;
    }

    public String getAge ()
    {
        return age;
    }

    public void setAge (String age)
    {
        this.age = age;
    }

    public String getErrorDetails ()
    {
        return errorDetails;
    }

    public void setErrorDetails (String errorDetails)
    {
        this.errorDetails = errorDetails;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [errorStackTrace = "+errorStackTrace+", stdout = "+stdout+", className = "+className+", stderr = "+stderr+", skipped = "+skipped+", duration = "+duration+", testActions = "+testActions+", name = "+name+", failedSince = "+failedSince+", skippedMessage = "+skippedMessage+", age = "+age+", errorDetails = "+errorDetails+", status = "+status+"]";
    }

  /*  @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cases) {


            *//*System.out.println("cb id 1 :"+((Cases) obj).cbId +" CB ID 2 ::"+ cbId +" cb id result :");
            if((((Cases) obj).cbId == cbId )){
                System.out.println("((Cases) obj).cbId == cbId ::"+((Cases) obj).cbId == cbId );
            }
           // System.out.println("((Cases) obj).cbId == cbId ::"+((Cases) obj).cbId == cbId );
            //System.out.println("deviceType 1 :"+((Cases) obj).deviceType +" deviceType deviceType 2 ::"+ deviceType +" result device type ="+ ((Cases) obj).deviceType == deviceType);
            //System.out.println("status 1 :"+((Cases) obj).status +" status 2 ::"+ status +" status result ::"+((Cases) obj).status == status);
            System.out.println("=============================");*//*
            //return ((Cases) obj).cbId == cbId && ((Cases) obj).deviceType == deviceType && ((Cases) obj).status == status ;

            System.out.println("((Cases) obj).cbId == cbId  :::"+ ((Cases) obj).cbId == cbId );
            return ((Cases) obj).cbId == cbId ;
        }
        return false;
    }

    @Override
    public int hashCode() {
        System.out.println("this.age ::::"+this.cbId);
        return Integer.parseInt(this.cbId);
    }*/
}