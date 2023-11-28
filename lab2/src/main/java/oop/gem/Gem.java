package oop.gem;

import oop.gem.enums.GemColor;
import oop.gem.enums.GemPreciousness;

public class Gem {
    private String name;
    private GemPreciousness preciousness;
    private String origin;
    private GemColor color;
    private double transparency;
    private int facetCount;
    private double value;    

    public Gem() {

    }

    public Gem(String name, GemPreciousness preciousness, String origin, GemColor color, double transparency, int facetCount, double value) {
        this.name = name;
        this.preciousness = preciousness;
        this.origin = origin;
        this.color = color;
        this.transparency = transparency;
        this. facetCount = facetCount;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GemPreciousness getPreciousness() {
        return preciousness;
    }

    public void setPreciousness(GemPreciousness preciousness) {
        this.preciousness = preciousness;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public GemColor getColor() {
        return color;
    }

    public void setColor(GemColor color) {
        this.color = color;
    }

    public double getTransparency() {
        return transparency;
    }

    public void setTransparency(double transparency) {
        if (transparency < 0 || transparency > 100) {
            System.out.println("Transparency must be greater than 0 and less than 100");
            return;
        }
        this.transparency = transparency;
    }

    public int getFacetCount() {        
        return facetCount;
    }

    public void setFacetCount(int facetCount) {
        if (facetCount < 4 || facetCount > 15) {
            System.out.println("Facet count must be greater than 3 and less than 16");
            return;
        }
        this.facetCount = facetCount;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if (value <= 0) {
            System.out.println("Value must be greater than 0");
            return;
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return "Gem{" +
                "name='" + name + '\'' +
                ", preciousness='" + preciousness.toString() + '\'' +
                ", origin='" + origin + '\'' +
                "color='" + color.toString() + '\'' +
                ", transparency=" + transparency +
                ", facetCount=" + facetCount +
                ", value=" + value +
                '}';
    }
}
