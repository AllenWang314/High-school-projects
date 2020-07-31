#test = open("blist.in", "w")
#test.write("3\n4 10 1\n8 13 3\n2 6 2")
#test.close()

inFile = open("blist.in", "r")
outFile = open("blist.out", "w")

num = int(inFile.readline())
arr = [0]*1001

for i in range(num):
    line = inFile.readline().split()
    arr[int(line[0])] = int(line[2])
    arr[int(line[1])] = -int(line[2])

b = 0
s = 0
for i in range (1001):
    s += arr[i]
    b = max(b,s)

#print(b)
outFile.write(str(b))
inFile.close()
outFile.close()
