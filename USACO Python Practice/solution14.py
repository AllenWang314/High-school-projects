#test = open("shell.in", "w")
#test.write("3\n1 2 1\n3 2 1\n1 3 1")
#test.close()

inFile = open("shell.in", "r")
outFile = open("shell.out", "w")

num = int(inFile.readline())
pts = [0,0,0]
cups = [0,1,2]

for i in range(num):
    line = inFile.readline().split()
    cups[int(line[0])-1], cups[int(line[1])-1] = cups[int(line[1])-1], cups[int(line[0])-1]
    pts[cups[int(line[2])-1]] += 1

#print(str(max(pts)))
outFile.write(str(max(pts)))
inFile.close()
outFile.close()
