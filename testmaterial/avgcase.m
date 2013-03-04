y = [linspace(1,100,100)',linspace(100,1,100)'];
save Ytrunc2 y;

%system("java -jar \"/cs/fs/home/aomarkka/NetBeansProjects/convexhull/dist/ConvexHull.jar\" circle100000 noat quick foobar draw ");

%system("java -jar \"/cs/fs/home/aomarkka/NetBeansProjects/convexhull/dist/ConvexHull.jar\" circle100000 noat gift foobar draw ");

%system("java -jar \"/cs/fs/home/aomarkka/NetBeansProjects/convexhull/dist/ConvexHull.jar\" circle100000 noat graham foobar draw ");
%{
for i=1:10
x = randn(100000*i,2);
save testrun x;
disp(['Gift with ', num2str(i), '000 points.']);  
system(["echo `java -jar \"../dist/ConvexHull.jar\" testrun noat 2 gift foobar no nodraw |grep Aver |cut -d ' ' -f 4` ", num2str(100000*i), " >> gift.avgtimes"]);
end

for i=1:10
x = randn(10000*i,2);
save testrun x;
disp(['Quick with ', num2str(i), '000 points.']);  
system(["echo `java -jar \"../dist/ConvexHull.jar\" testrun noat 2 quick foobar no nodraw |grep Aver |cut -d ' ' -f 4` ", num2str(10000*i), " >> quick.avgtimes"]);
end

for i=1:10
x = randn(10000*i,2);
save testrun x;
disp(['Graham with ', num2str(i), '000 points.']);  
system(["echo `java -jar \"../dist/ConvexHull.jar\" testrun noat 2 graham foobar no nodraw |grep Aver |cut -d ' ' -f 4` ", num2str(10000*i), " >> graham.avgtimes"]);
end

for i=1:10
x = randn(1000000*i,2);
save testrun x;
disp(['Quick with ', num2str(i), '0000 points.']);  
system(["echo `java -jar \"../dist/ConvexHull.jar\" testrun noat 2 quick foobar no nodraw |grep Aver |cut -d ' ' -f 4` ", num2str(1000000*i), " >> quick.avgtimes"]);
end

for i=1:10
x = randn(1000000*i,2);
save testrun x;
disp(['Graham with ', num2str(i), '0000 points.']);  
system(["echo `java -jar \"../dist/ConvexHull.jar\" testrun noat 2 graham foobar no nodraw |grep Aver |cut -d ' ' -f 4` ", num2str(1000000*i), " >> graham.avgtimes"]);
end
%}
for i=1:10
x = randn(1000000*i,2);
save testrun x;
disp(['Gift with ', num2str(i), '000 points.']);  
system(["echo `java -jar \"../dist/ConvexHull.jar\" testrun noat 2 gift foobar no nodraw |grep Aver |cut -d ' ' -f 4` ", num2str(1000000*i), " >> gift.avgtimes"]);
end

