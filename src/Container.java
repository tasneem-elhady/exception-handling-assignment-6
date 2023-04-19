//implements comparable to override compareTo
public class Container implements Comparable<Container> {
    //datafields
    public String UUID;
    public String LONG_NAME;
    public String SHORT_NAME;
    public Container(String id, String ln, String sn)
    {
        UUID = id;
        LONG_NAME = ln;
        SHORT_NAME = sn;
    }

    //to be able to use collections.sort with a list of containers correctly
    @Override
    public int compareTo(Container c) {
        return this.SHORT_NAME.compareTo(c.SHORT_NAME);
    }
}