package regressionexcellcreator.objects;

public class Suites
{
    private String duration;

    private Cases[] cases;

    private String stdout;

    private String name;

    private int id;

    private String stderr;

    private String timestamp;

    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
    }

    public Cases[] getCases ()
    {
        return cases;
    }

    public void setCases (Cases[] cases)
    {
        this.cases = cases;
    }

    public String getStdout ()
    {
        return stdout;
    }

    public void setStdout (String stdout)
    {
        this.stdout = stdout;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getStderr ()
    {
        return stderr;
    }

    public void setStderr (String stderr)
    {
        this.stderr = stderr;
    }

    public String getTimestamp ()
    {
        return timestamp;
    }

    public void setTimestamp (String timestamp)
    {
        this.timestamp = timestamp;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [duration = "+duration+", cases = "+cases+", stdout = "+stdout+", name = "+name+", id = "+id+", stderr = "+stderr+", timestamp = "+timestamp+"]";
    }
}