#test = open("herding.in", "w")
#test.write("4 7 9")
#test.close()

inFile = open("herding.in", "r")
outFile = open("herding.out", "w")
line = inFile.readline().split()

for i in range(3):
    line[i] = int(line[i])
    
line = sorted(line)

first = line[1]-line[0]
second = line[2]-line[1]

M = max(line[1]-line[0]-1,line[2]-line[1]-1)

if first == 1 and second == 1:
    m = 0
elif first > 2 and second == 1 or second > 2 and first == 1:
    m = 2
elif first == 2 or second == 2:
    m = 1
else:
    m = 2

outFile.write(str(m) + "\n" + str(M))

inFile.close()
outFile.close()
    
