#test = open("lifeguards.in", "w")
#test.write("3\n5 9\n1 4\n3 7")
#test.close()
inFile = open("lifeguards.in", "r")
outFile = open("lifeguards.out", "w")

numCows = int(inFile.readline())
interval = [0]*1000
startStop = []

for i in range(numCows):
    startStop.append(inFile.readline())
    line = startStop[i].split()
    for j in range(int(line[0]),int(line[1])):
        interval[j] += 1

totalTime = 0
minMissing = 1000;

for i in range(1000):
    if interval[i] > 0:
        totalTime += 1

for i in range(numCows):
    current = 0
    line = startStop[i].split()
    for j in range(int(line[0]),int(line[1])):
        if interval[j] == 1:
            current += 1
    #print(current)
    minMissing = min(minMissing,current)

#print(minMissing)
#print(interval)
#print(totalTime-minMissing)
outFile.write(str(totalTime-minMissing))
outFile.close()
inFile.close()
