#test = open("teleport.in", "w")
#test.write("3 10 8 2")
#test.close()

inFile = open("teleport.in", "r")
outFile = open("teleport.out", "w")
line = inFile.readline()
line = line.split()
nums = []
for i in range(4):
    nums.append(int(line[i]))

dist = abs(nums[0] - nums[1])
dist = min(dist,abs(nums[0] - nums[2]) + abs(nums[3] - nums[1]))
dist = min(dist,abs(nums[0] - nums[3]) + abs(nums[1] - nums[2]))

print(dist)

outFile.write(str(dist))
outFile.close()
inFile.close()
