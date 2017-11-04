import java.util.LinkedList;
public class Path {
    int size;
    int pathDistance;
    double density;
    int totalCars;
    LinkedList<bBox> locations;
    public Path(int psize)
    {
       size =  psize;
       pathDistance = 0;
       totalCars = 0;
       for(int i = 0; i < size; i++)
       {
           locations.add(new bBox());
           pathDistance += locations.get(i).getDistance();
           totalCars += locations.get(i).numCars();
       }
       density = (double)totalCars / pathDistance;
    }
    
    public boolean addCar()
    {
        return locations.get(0).addCar();
    }
    public int getPathDistance()
    {
        return pathDistance;
    }
    public double getAverageSpeed()
    {
        int averageSpeed = 0;
        int count = 0;
        for(bBox b : locations)
        {
            if(b.getAverageSpeed() >0)
            {
                count++;
            }
            averageSpeed += b.getAverageSpeed();
        }
        return averageSpeed/count;
    }
    public double getPathDensity()
    {
        return density;
    }
    public boolean

}
