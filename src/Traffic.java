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
import java.util.Scanner;
import java.util.ArrayList;
public class Traffic {
    static ArrayList<Path> paths;
    static ArrayList aSubPaths;
    static ArrayList bSubPaths;
    static ArrayList cSubPaths;
    static ArrayList dSubPaths;
    public static void main(String[] args)
    {
        paths = new ArrayList();
        aSubPaths = new ArrayList();
        bSubPaths = new ArrayList();
        cSubPaths = new ArrayList();
        dSubPaths = new ArrayList();
        
        initializePaths();
        
        int algorithm = 1;
        
        int average1 = run(algorithm);
        
        algorithm = 2;
        
        int average2 = run (algorithm);
        
        
       
        
    }
    public static int run(int alg)
    {
       int minTraffic = findIndexOfMinTraffic(); 
       if (paths.get(minTraffic).isTraffic())
       {
           if(alg == 1)
           {
              algorithm1(paths); 
           }
           else if(alg == 2)
           {
               algorithm2(paths);
           }
       }
       else
       {
           paths.get(minTraffic).addCar();
       }
    }
    
    public static int findIndexOfMinTraffic()
    {
        
    }

    /**
     * return the travel time for the given path
     * @param currentPath the path to return travel time from
     * @return int travelTime
     */
    public static int findTravelTime(Path currentPath) {
        return currentPath.getPathDistance() / currentPath.getAverageSpeed();
    }

    /**
     * calculate where the next car should be placed, based on Algorithm 1
     * @param currentPath the ArrayList<Path> of all paths for the current node
     */
    public static void algorithm1(ArrayList<Path> currentPaths)
    {
        //What is the path with the fastest travel time?
        Path shortPath = findShortestTime(currentPaths); //Change: being worked on.

        //add car to shortPath
        shortPath.addCar();


    }

    /**
     * calculate where the next car should be placed, based on Algorithm 1
     * @param currentPath the ArrayList<Path> of all paths for the current node
     */
    public static void algorithm2(ArrayList<Path> currentPaths)
    {
        //What is the path with the fastest travel time?
        Path shortPath = findShortestTime(currentPaths); //Change: being worked on.

        //Is the time acceptable? if not, go into the new part of the algorithm.
        if (shortTime <= accepltableTime) {
            //add car to this path
            shortPath.addCar();
        } else {
            double[] weights = new double[currentPaths.size()];
            double totalWeight = 0.0;
            double bestWeight = 0.0;

            //loop through the paths, calculating the weight form the density and time
            for (int i = 0; i < currentPaths.size(); i++) {
                //determine the current path weight, needs revision!
                weights[i] = 0.5 * currentPaths.get(i).getPathDesnsity() + 0.5 * findTravelTime(currentPaths.get(i));
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
            currentPaths.get(chosenPathIndex).addCar();
        }
    }
    
    public static void initializePaths()
    {
        paths.add(new Path(30000));
        paths.add(new Path(30000));
        paths.add(new Path(30000));
        paths.add(new Path(30000));
        
        aSubPaths.add(new Path(30000));
        aSubPaths.add(new Path(30000));
        aSubPaths.add(new Path(30000));
        aSubPaths.add(new Path(30000));
        
        bSubPaths.add(new Path(30000));
        bSubPaths.add(new Path(30000));
        bSubPaths.add(new Path(30000));
        bSubPaths.add(new Path(30000));
        
        cSubPaths.add(new Path(30000));
        cSubPaths.add(new Path(30000));
        cSubPaths.add(new Path(30000));
        cSubPaths.add(new Path(30000));
        
        dSubPaths.add(new Path(30000));
        dSubPaths.add(new Path(30000));
        dSubPaths.add(new Path(30000));
        dSubPaths.add(new Path(30000));
    }
}
