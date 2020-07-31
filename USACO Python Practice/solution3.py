fin = open ('hoofball.in','r')
fout = open ('hoofball.out','w')

numCows = int(fin.readline())
cowList = fin.readline()
cowList = cowList.split()

for i in range(numCows):
    cowList[i] = int(cowList[i])

cowList.sort()
afterTarget = [0] * numCows

def goToTarget(index, afterTarget):
    visited = [0] * numCows
    while(visited[index] == 0):
        afterTarget[index] += 1
        visited[index] += 1
        if index == 0:
            index = 1
        elif index == numCows - 1:
            index = numCows - 2
        elif (cowList[index] - cowList[index - 1]) < (cowList[index+1] - cowList[index]):
            index -= 1
        else:
            index += 1

for i in range(numCows):
    goToTarget(i, afterTarget)

var = 0
for i in range(numCows):
    if afterTarget[i] == 1:
        var+=1
        
print(afterTarget)
print(var)

fout.write(str(var))
fout.close()
