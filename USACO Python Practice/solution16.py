#test = open("guess.in", "w")
#test.write("4\nbird 2 flies eatsworms\n\
#           cow 4 eatsgrass isawesome makesmilk goesmoo\n\
#           sheep 1 eatsgrass\n\
#           goat 2 makesmilk eatsgrass")
#test.close()

inFile = open("guess.in", "r")
outFile = open("guess.out", "w")
N = int(inFile.readline())

arr = []

for i in range(N):
    line = inFile.readline().split()[2:]
    arr.append(line)

#print(arr)

ans = 0

for i in range(N):
    for j in range(i):
        sim = 0
        for trait in arr[i]:
            sim += (trait in arr[j])
        ans = max(ans, sim)

outFile.write(str(ans+1))
inFile.close()
outFile.close()
