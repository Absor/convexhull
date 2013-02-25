y = [linspace(1,100,100)',linspace(100,1,100)'];
save Ytrunc2 y;

%system("java -jar \"/cs/fs/home/aomarkka/NetBeansProjects/convexhull/dist/ConvexHull.jar\" circle100000 noat quick foobar draw ");

%system("java -jar \"/cs/fs/home/aomarkka/NetBeansProjects/convexhull/dist/ConvexHull.jar\" circle100000 noat gift foobar draw ");

%system("java -jar \"/cs/fs/home/aomarkka/NetBeansProjects/convexhull/dist/ConvexHull.jar\" circle100000 noat graham foobar draw ");

for i=1:10
y = [linspace(1,100,1000*i)',linspace(1,100,1000*i)'];
x = [sin(y(:,1)),cos(y(:,2))];
save testrun x;
disp(['Gift with ', num2str(i), '000 points.']);  
system(["echo `java -jar \"/cs/fs/home/aomarkka/NetBeansProjects/convexhull/dist/ConvexHull.jar\" testrun noat 2 gift foobar nodraw |grep Aver |cut -d ' ' -f 4` ", num2str(1000*i), " >> gift.worsttimes"]);
end

for i=1:10
y = [linspace(1,100,1000*i)',linspace(1,100,1000*i)'];
x = [sin(y(:,1)),cos(y(:,2))];
save testrun x;
disp(['Quick with ', num2str(i), '000 points.']);  
system(["echo `java -jar \"/cs/fs/home/aomarkka/NetBeansProjects/convexhull/dist/ConvexHull.jar\" testrun noat 2 quick foobar nodraw |grep Aver |cut -d ' ' -f 4` ", num2str(1000*i), " >> quick.worsttimes"]);
end

for i=1:10
y = [linspace(1,100,1000*i)',linspace(1,100,1000*i)'];
x = [sin(y(:,1)),cos(y(:,2))];
save testrun x;
disp(['Graham with ', num2str(i), '000 points.']);  
system(["echo `java -jar \"/cs/fs/home/aomarkka/NetBeansProjects/convexhull/dist/ConvexHull.jar\" testrun noat 2 graham foobar nodraw |grep Aver |cut -d ' ' -f 4` ", num2str(1000*i), " >> graham.worsttimes"]);
end

for i=1:10
y = [linspace(1,100,100000*i)',linspace(1,100,100000*i)'];
x = [sin(y(:,1)),cos(y(:,2))];
save testrun x;
disp(['Quick with ', num2str(i), '0000 points.']);  
system(["echo `java -jar \"/cs/fs/home/aomarkka/NetBeansProjects/convexhull/dist/ConvexHull.jar\" testrun noat 2 quick foobar nodraw |grep Aver |cut -d ' ' -f 4` ", num2str(100000*i), " >> quick.worsttimes"]);
end

for i=1:10
y = [linspace(1,100,100000*i)',linspace(1,100,100000*i)'];
x = [sin(y(:,1)),cos(y(:,2))];
save testrun x;
disp(['Graham with ', num2str(i), '0000 points.']);  
system(["echo `java -jar \"/cs/fs/home/aomarkka/NetBeansProjects/convexhull/dist/ConvexHull.jar\" testrun noat 2 graham foobar nodraw |grep Aver |cut -d ' ' -f 4` ", num2str(100000*i), " >> graham.worsttimes"]);
end
