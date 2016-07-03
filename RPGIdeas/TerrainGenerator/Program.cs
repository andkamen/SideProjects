using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TerrainGenerator
{
    class Program
    {
        static Random rng = new Random();
        static List<char> path = new List<char>();
        private static int width = 10;
        private static int height = 10;
        private static int genCount = 0;
        private static int totalGenerations = 0;
        private static bool isPrinted = false;

        private static char[] allObjects = { ' ', ' ', ' ', ' ', ' ', ' ', 'T', 'T', 'H' };
        private static char[] impassableObj = { 'T', 'R', 'H' };
        private static char[] passableObj = { ' ' };

        private static char[,] labyrinth =
        {
            {'s',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ','e'},
            {' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' '}
        };

        public static void Main()
        {
            System.IO.StreamWriter file = new System.IO.StreamWriter("RGT_Log.txt");
            file.WriteLine("RANDOMLY GENERATED TERRAIN LOG");
            file.Close();

            GenerateLabyrinth();
            Console.WriteLine();
            while (!FindPathToExit())
            {
                GenerateLabyrinth();
            }
            Console.WriteLine(genCount);

        }

        private static void GenerateLabyrinth()
        {
            System.IO.StreamWriter file = new System.IO.StreamWriter("RGT_Log.txt", true);
            file.WriteLine("Starting labyrinth generation...");

            labyrinth = new char[height, width];

            labyrinth[1, 0] = 's';
            labyrinth[height / 2, width - 1] = 'e';

            for (int row = 0; row < labyrinth.GetLength(0); row++)
            {
                for (int col = 0; col < labyrinth.GetLength(1); col++)
                {
                    if (labyrinth[row, col] == 's' || labyrinth[row, col] == 'e')
                    {
                        continue;
                    }
                    labyrinth[row, col] = allObjects[rng.Next(allObjects.Length)];
                }
            }
            genCount++;
            isPrinted = false;
            file.WriteLine("Finished labyrinth generation...");
            file.Close();

        }

        static bool FindPathToExit()
        {
            for (int row = 0; row < labyrinth.GetLength(0); row++)
            {
                for (int col = 0; col < labyrinth.GetLength(1); col++)
                {
                    if (labyrinth[row, col] == 's')
                    {
                        bool foundDirection = TryDirection(row, col + 1, 'R') ||
                                              TryDirection(row - 1, col, 'U') ||
                                              TryDirection(row + 1, col, 'D') ||
                                              TryDirection(row, col - 1, 'L');
                        PrintLabyrinth();

                        return foundDirection;
                    }
                }
            }
            return false;
        }

        private static bool TryDirection(int row, int col, char direction)
        {
            if (!InRange(row, col))
            {
                return false;
            }

            path.Add(direction);
            //PrintPath(path);

            if (labyrinth[row, col] == 'e')
            {
                // isPrinted = false;
                //PrintPath(path);
                // PrintLabyrinth();
                return true;
            }

            if (labyrinth[row, col] == ' ')
            {
                labyrinth[row, col] = '.';

                if (TryDirection(row, col + 1, 'R'))
                {
                    return true;
                }
                if (TryDirection(row - 1, col, 'U'))
                {
                    return true;
                }
                if (TryDirection(row + 1, col, 'D'))
                {
                    return true;
                }
                if (TryDirection(row, col - 1, 'L'))
                {
                    return true;
                }


                 labyrinth[row, col] = ' ';
            }

            path.RemoveAt(path.Count - 1);
            return false;
        }

        static bool InRange(int row, int col)
        {
            bool rowInRange = row >= 0 && row < labyrinth.GetLength(0);
            bool colInRange = col >= 0 && col < labyrinth.GetLength(1);

            return rowInRange && colInRange;
        }

        static void PrintLabyrinth()
        {
            for (int row = 0; row < labyrinth.GetLength(0); row++)
            {
                for (int col = 0; col < labyrinth.GetLength(1); col++)
                {
                    Console.Write("{0} ", labyrinth[row, col]);
                }
                Console.WriteLine();
            }


            System.IO.StreamWriter file = new System.IO.StreamWriter("RGT_Log.txt", true);
            file.WriteLine("CURRENT GENERATION COUNT: {0}", genCount);

            for (int row = 0; row < labyrinth.GetLength(0); row++)
            {
                for (int col = 0; col < labyrinth.GetLength(1); col++)
                {
                    file.Write("{0} ", labyrinth[row, col]);
                }
                file.WriteLine();
            }
            file.Close();
            isPrinted = true;
        }

        static void PrintPath(List<char> path)
        {
            // Console.Write("Found path to the exit: ");
            foreach (var dir in path)
            {
                Console.Write(dir);
            }
            Console.WriteLine();

            System.IO.StreamWriter file = new System.IO.StreamWriter("RGT_Log.txt", true);
            foreach (var dir in path)
            {
                file.Write(dir);
            }
            file.WriteLine();
            file.Close();

        }
    }
}
