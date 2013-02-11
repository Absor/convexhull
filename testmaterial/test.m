y = [linspace(1,100,100)',linspace(100,1,100)'];
save Ytrunc2 y;

system("java -jar \"/cs/fs/home/aomarkka/NetBeansProjects/convexhull/dist/ConvexHull.jar\" circle100000 noat quick foobar draw ");

system("java -jar \"/cs/fs/home/aomarkka/NetBeansProjects/convexhull/dist/ConvexHull.jar\" circle100000 noat gift foobar draw ");

system("java -jar \"/cs/fs/home/aomarkka/NetBeansProjects/convexhull/dist/ConvexHull.jar\" circle100000 noat graham foobar draw ");

