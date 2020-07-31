fin = open ('shuffle.in', 'r')
fout = open ('shuffle.out', 'w')
lineList = fin.readlines()

numCows = int(lineList[0])
permList = lineList[1].split()
cowList = lineList[2].split()
newPermList = []
newCowPermList = []

for i in range(numCows):
    newPermList.append(0)
    newCowPermList.append(0)

for i in range(numCows):
    newPermList[int(permList[i])-1] = i+1

for i in range(numCows):
    newCowPermList[int(newPermList[int(newPermList[int(newPermList[i])-1])-1])-1] = cowList[i]

answer = ""

for i in range(numCows):
    answer += newCowPermList[i] + "\n"



fout.write(str(answer[:-1]))

fout.close()
