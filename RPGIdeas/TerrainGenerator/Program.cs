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
        private static int width = 20;
        private static int height = 15;
        private static int genCount = 0;
        private static int totalGenerations = 0;

        private static char[] allObjects = new[] { ' ', ' ', ' ', 'T', 'R', 'H' };
        private static char[] impassableObj = new[] { 'T', 'R', 'H' };

        private static char[,] labyrinth =
        {
            {'s',' ',' ',' ',' ',' '},
            {' ','*','*',' ','*',' '},
            {' ','*','*',' ','*',' '},
            {' ','*','e',' ',' ',' '},
            {' ',' ',' ','*',' ',' '}
        };

        public static void Main()
        {
            Console.WriteLine("HERE BE DRAGONS!\n");

            //while (genCount < 400)
            //{
            //    genCount = 0;
            //    totalGenerations++;
                GenerateLabyrinth();

                while (!FindPathToExit())
                {
                    GenerateLabyrinth();
                }
                PrintLabyrinth();
               // Console.ReadLine();
                Console.WriteLine(genCount);
            //}
            //Console.WriteLine(totalGenerations);
        }

        private static void GenerateLabyrinth()
        {
            genCount++;
            labyrinth = new char[height, width];

            labyrinth[(height / 2) - 1, 0] = 's';
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
        }

        static bool FindPathToExit()
        {
            for (int row = 0; row < labyrinth.GetLength(0); row++)
            {
                for (int col = 0; col < labyrinth.GetLength(1); col++)
                {
                    if (labyrinth[row, col] == 's')
                    {
                        return TryDirection(row, col - 1, 'L') ||
                            TryDirection(row - 1, col, 'U') ||
                            TryDirection(row, col + 1, 'R') ||
                            TryDirection(row + 1, col, 'D');
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

            if (labyrinth[row, col] == 'e')
            {
                //PrintPath(path);
                //PrintLabyrinth();
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
        }

        static void PrintPath(List<char> path)
        {
            Console.Write("Found path to the exit: ");
            foreach (var dir in path)
            {
                Console.Write(dir);
            }
            Console.WriteLine();
        }
    }
}
