import numpy as np
def read_file(filename):
    # read file
    with open(filename) as f:
        lines = f.readlines()
    output = [str.rstrip(line) for line in lines]
    return output

data = read_file('./day6.txt')[0].split(',')
data = [int(val) for val in data]
# fish per day
days = [3] * 9

# read fish
for day in data:
    days[day] += 1

def step(days):
    birth = days.pop(0)     # fish that will give birth
    days[6] += birth        # reset their counter
    days.append(birth)      # new fish
# after 80 days
for _ in range(80):
    step(days)

print(sum(days))

# after 256 days
for _ in range(256 - 80):
    step(days)

print(sum(days))