using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TerrainGenerator2
{
    class Program
    {
        //change matrix1 to grid if you want to see results for that data.
        private static readonly Random rng = new Random();
        private static int width = 30;
        private static int height = 30;
        private static int genCount = 0;
        private static readonly int[] AllObjects = { 0, 0, 0, 0, 0, 0, 3, 3, 3, 3 };
        private static readonly int[] ImpassableObj = { 3,4,5 };
        //0 - ground ; 1 - Start; 2- Exit
        private static readonly int[] PassableObj = { 0,1,2 };

        private static int[,] grid;
        private static int[,] gridTraced;

        private static HashSet<Tuple<int, int>> passableArea = new HashSet<Tuple<int, int>>();

        public static void Main()
        {
            GenerateLabyrinth();
            
            while (!FindConnectedAreas())
            {
                GenerateLabyrinth();
            }

            PrintLabyrinth();
            Console.WriteLine("---------------------------------------------------");
            PrintTracedLabyrinth();
            Console.WriteLine(genCount);
        }

        private static int[,] CopyGrid(int[,] grid)
        {
            int[,] coppiedGrid = new int[grid.GetLength(0), grid.GetLength(1)];

            for (int i = 0; i < grid.GetLength(0); i++)
            {
                for (int j = 0; j < grid.GetLength(1); j++)
                {
                    coppiedGrid[i, j] = grid[i, j];
                }
            }
            return coppiedGrid;
        }

        static bool FindConnectedAreas()
        {
            int exitRow = 0;
            int exitCol = 0;

            for (int row = 0; row < gridTraced.GetLength(0); row++)
            {
                for (int col = 0; col < gridTraced.GetLength(1); col++)
                {
                    //Finds the area that is connected to the start cell
                    if (gridTraced[row, col] == 1)
                    {
                        TryDirection(row, col - 1, 'L');
                        TryDirection(row - 1, col, 'U');
                        TryDirection(row, col + 1, 'R');
                        TryDirection(row + 1, col, 'D');
                    }
                    if (gridTraced[row, col] == 2)
                    {
                        exitCol = col;
                        exitRow = row;
                    }
                }
            }

            if (passableArea.Contains(new Tuple<int, int>(exitRow, exitCol)))
            {
                return true;
            }

            return false;
        }

        private static void TryDirection(int row, int col, char direction)
        {
            if (!InRange(row, col))
            {
                return;
            }

            if (PassableObj.Contains(gridTraced[row, col]))
            {
                passableArea.Add(new Tuple<int, int>(row, col));

                //9 is any number not a passable or impassable object so as not to cause
                // stack overflow or to terminate the search prematurely
                gridTraced[row, col] = 9;  

                TryDirection(row, col - 1, 'L'); // left
                TryDirection(row - 1, col, 'U'); // up
                TryDirection(row, col + 1, 'R'); // right
                TryDirection(row + 1, col, 'D'); // down
            }
        }

        static bool InRange(int row, int col)
        {
            bool rowInRange = row >= 0 && row < gridTraced.GetLength(0);
            bool colInRange = col >= 0 && col < gridTraced.GetLength(1);

            return rowInRange && colInRange;
        }

        static void PrintLabyrinth()
        {
            for (int row = 0; row < grid.GetLength(0); row++)
            {
                for (int col = 0; col < grid.GetLength(1); col++)
                {
                    Console.Write("{0} ", grid[row, col]);
                }
                Console.WriteLine();
            }
        }

        static void PrintTracedLabyrinth()
        {
            for (int row = 0; row < gridTraced.GetLength(0); row++)
            {
                for (int col = 0; col < gridTraced.GetLength(1); col++)
                {
                    Console.Write("{0} ", gridTraced[row, col]);
                }
                Console.WriteLine();
            }
        }


        private static void GenerateLabyrinth()
        {
            grid = new int[height, width];
            
            for (int row = 0; row < grid.GetLength(0); row++)
            {
                for (int col = 0; col < grid.GetLength(1); col++)
                {
                    grid[row, col] = AllObjects[rng.Next(AllObjects.Length)];
                }
            }
            //place entry and exit point
            grid[1, 0] = 1;
            grid[height / 2, width - 1] = 2;

            genCount++;
            gridTraced = CopyGrid(grid);
        }
    }
}
