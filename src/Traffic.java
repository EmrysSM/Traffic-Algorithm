/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alewis91
 */
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;
public class Traffic {
    static ArrayList<Path> paths;
    static ArrayList aSubPaths;
    static ArrayList bSubPaths;
    static ArrayList cSubPaths;
    static ArrayList dSubPaths;
    static int totalCount = 0;
    public static void main(String[] args)
    {
        paths = new ArrayList();
        aSubPaths = new ArrayList();
        bSubPaths = new ArrayList();
        cSubPaths = new ArrayList();
        dSubPaths = new ArrayList();
        
        initializePaths();
        
        int algorithm = 1;
        
        double average1 = run(algorithm);
        System.out.println(average1);
        algorithm = 2;
        totalCount = 0;
        double average2 = run (algorithm);
        System.out.println(average2);
        
       
        
    }
    public static double run(int alg)
    {
        int numCarsAdded = 100, count = 0;
        for(int i = 0; i < numCarsAdded; i++)
        {
            count++;
           if(alg == 1)
           {
              algorithm1(paths, count); 
              /**algorithm1(aSubPaths);
              algorithm1(bSubPaths);
              algorithm1(cSubPaths);
              algorithm1(dSubPaths);
              */
           }
           else if(alg == 2)
           {
              algorithm2(paths, count);
//              
//              algorithm2(aSubPaths);
//              algorithm2(bSubPaths);
//              algorithm2(cSubPaths);
//              algorithm2(dSubPaths);
           }  
           advanceCars(paths, count);
//           advanceCars(aSubPaths);
//           advanceCars(bSubPaths);
//           advanceCars(cSubPaths);
//           advanceCars(dSubPaths);
        }
        while(advanceCars(paths, count)/*||
           advanceCars(aSubPaths) ||
           advanceCars(bSubPaths) ||
           advanceCars(cSubPaths) ||
           advanceCars(dSubPaths)*/)
        {
            count++;
        }
        return (double) totalCount / numCarsAdded;
    }
    

    /**
     * return the travel time for the given path
     * @param currentPath the path to return travel time from
     * @return int travelTime
     */
    public static double findTravelTime(Path currentPath) {
        if (!(currentPath.getAverageSpeed() == 0)) {
            return currentPath.getPathDistance() / currentPath.getAverageSpeed();
        } else {
            return currentPath.getPathDistance();
        }
    }

    /**
     * calculate where the next car should be placed, based on Algorithm 1
     * @param currentPath the ArrayList<Path> of all paths for the current node
     */
    
    
    public static Path getShortestTime()
    {
        Path minPath = paths.get(0);
        double minTime = findTravelTime(paths.get(0));
        for(int i=1; i< paths.size(); i++)
        {     
            if(findTravelTime(paths.get(i)) < findTravelTime(minPath))
            {
                minPath = paths.get(i); 
                minTime = findTravelTime(minPath);
            }
         }
        return minPath;
    }
    
    public static void algorithm1(ArrayList<Path> currentPaths, int count)
    {
        //What is the path with the fastest travel time?
        Path shortPath = getShortestTime(); //Change: being worked on.

        //add car to shortPath
        shortPath.addCar(count);


    }

    /**
     * calculate where the next car should be placed, based on Algorithm 1
     * @param currentPaths the ArrayList<Path> of all paths for the current node
     */
    public static void algorithm2(ArrayList<Path> currentPaths, int count)
    {
        //What is the path with the fastest travel time?
        Path shortPath = getShortestTime(); //Change: being worked on.

        //Is the time acceptable? if not, go into the new part of the algorithm.
        double shortTime = findTravelTime(shortPath), acceptableTime = 1800;
        if (shortTime <= acceptableTime) {
            //add car to this path
            shortPath.addCar(count);
        } else {
            double[] weights = new double[currentPaths.size()];
            double totalWeight = 0.0;
            double bestWeight = 0.0;

            //loop through the paths, calculating the weight form the density and time
            for (int i = 0; i < currentPaths.size(); i++) {
                //determine the current path weight, needs revision!
                weights[i] = 0.5 * currentPaths.get(i).getPathDensity() + 0.5 * findTravelTime(currentPaths.get(i));
                totalWeight += weights[i];
            }

            double[] weightProb = new double[weights.length];

            //calculate the probability of each path
            for (int i = 0; i < weights.length; i++) {
                weightProb[i] = weights[i] / totalWeight;
            }

            //Sort the Array for discovering where the random number will fit
            Arrays.sort(weightProb);

            //generate random number for path selection
            Random rand = new Random();
            double randPath = rand.nextDouble();

            int chosenPathIndex = weightProb.length - 1;
            //Compare the random number to the paths
            for (int i = 0; i < weightProb.length; i++) {
                if (randPath < weightProb[i]) {
                    chosenPathIndex = i - 1;
                }
            }

            //translate back to the correct index, due to sorting
            for (int i = 0; i < weights.length; i++) {
                double prob = weights[i] / totalWeight;
                if (prob == weightProb[chosenPathIndex]) {
                    chosenPathIndex = i;
                }
            }

            //add the car to the chosen path
            currentPaths.get(chosenPathIndex).addCar(count);
        }
    }
    
    public static boolean advanceCars(ArrayList<Path> myPaths, int count)
    {
        boolean isCars = false;
        for(Path p : myPaths)
        {
            for(int j = 0; j < p.getLocations().size(); j++)
            {
                for(int i = 0; i < p.getLocations().get(j).getCars().size();i++)
                {
                    p.getLocations().get(j).getCars().get(i).addDistanceInCurrBox(p.getLocations().get(j).getCars().get(i).getSpeed());
                    isCars = true;
                    
                    if(p.getLocations().get(j).getCars().get(i).getDistanceInCurrBox() >= p.getLocations().get(j).getDistance())
                    {
                        if(p.getLocations().get(p.getLocations().size() - 1) == p.getLocations().get(j))
                        {
                            totalCount += count - p.getLocations().get(j).getCars().get(i).getCount();
                            p.getLocations().get(j).getCars().remove(p.getLocations().get(j).getCars().get(i));
                            continue;
                        }
                        p.getLocations().get(j).getCars().get(i).setDistanceInCurrBox(p.getLocations().get(j).getCars().get(i).getDistanceInCurrBox() - p.getLocations().get(j).getDistance());
                        p.getLocations().get(i+1).addCar(p.getLocations().get(j).getCars().get(i));
                        p.getLocations().get(i).getCars().remove(p.getLocations().get(j).getCars().get(i));
                    }
                }
            }
        }
        return isCars;
    }
    
    public static void initializePaths()
    {
        paths.add(new Path(2000));
        paths.add(new Path(2000));
        paths.add(new Path(1000));
        paths.add(new Path(2000));
        
//        aSubPaths.add(new Path(2000));
//        aSubPaths.add(new Path(2000));
//        aSubPaths.add(new Path(2000));
//        aSubPaths.add(new Path(2000));
//
//        bSubPaths.add(new Path(2000));
//        bSubPaths.add(new Path(2000));
//        bSubPaths.add(new Path(2000));
//        bSubPaths.add(new Path(2000));
//
//        cSubPaths.add(new Path(2000));
//        cSubPaths.add(new Path(2000));
//        cSubPaths.add(new Path(2000));
//        cSubPaths.add(new Path(2000));
//
//        dSubPaths.add(new Path(2000));
//        dSubPaths.add(new Path(2000));
//        dSubPaths.add(new Path(2000));
//        dSubPaths.add(new Path(2000));
    }
}
