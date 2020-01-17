package regressionexcellcreator.objects;

public class MainClass
{
    private String duration;

    private String[] testActions;

    private Suites[] suites;

    private String failCount;

    private String _class;

    private String skipCount;

    private String passCount;

    private String empty;

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

    public Suites[] getSuites ()
    {
        return suites;
    }

    public void setSuites (Suites[] suites)
    {
        this.suites = suites;
    }

    public String getFailCount ()
    {
        return failCount;
    }

    public void setFailCount (String failCount)
    {
        this.failCount = failCount;
    }

    public String get_class ()
    {
        return _class;
    }

    public void set_class (String _class)
    {
        this._class = _class;
    }

    public String getSkipCount ()
    {
        return skipCount;
    }

    public void setSkipCount (String skipCount)
    {
        this.skipCount = skipCount;
    }

    public String getPassCount ()
    {
        return passCount;
    }

    public void setPassCount (String passCount)
    {
        this.passCount = passCount;
    }

    public String getEmpty ()
    {
        return empty;
    }

    public void setEmpty (String empty)
    {
        this.empty = empty;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [duration = "+duration+", testActions = "+testActions+", suites = "+suites+", failCount = "+failCount+", _class = "+_class+", skipCount = "+skipCount+", passCount = "+passCount+", empty = "+empty+"]";
    }
}