test = open("measurement.in", "w")
test.write("4 10\n7 3 +3\n4 2 -1\n9 3 -1\n1 1 +2")
test.close()

inFile = open("measurement.in", "r")
outFile = open("measurement.out", "w")

data, initial = inFile.readline().split()
data = int(data)
initial = int(initial)
dayList = []

class Day:

	def __init__(self, string):
		self.day, self.cow, self.change = string.split()
		self.day = int(self.day)
		self.cow = int(self.cow)
		self.change = int(self.change)

for i in range(data):
	dayList.append(Day(inFile.readline()))
dayList.sort(key=lambda x: x.day)

i = 0
best
while 

inFile.close()
outFile.close()