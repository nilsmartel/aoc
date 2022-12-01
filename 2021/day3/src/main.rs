fn main() {
    let input = include_str!("../input");

    let nums = input
        .lines()
        .map(|s| u16::from_str_radix(s, 2).unwrap())
        .collect::<Vec<_>>();

    // get leftmost bit of the numbers

    let mask = nums.iter().map(|n| !n).reduce(|a, b| a | b).unwrap();

    let leftmost = (0..16u16)
        .map(|n| if (1 << n & mask) > 0 { n } else { 0 })
        .max()
        .unwrap();

    println!("number of bits: {leftmost}");

    let mut fields = vec![0u32; leftmost as usize];

    for num in &nums {
        for i in 0..leftmost {
            let bit = num & i;

            if bit > 0 {
                fields[i as usize] += 1;
            }
        }
    }

    // fields to number!
    let mid = (nums.len() / 2) as u32;
    let gamma = fields
        .iter()
        .enumerate()
        .map(|(index, amount)| {
            if *amount > mid {
                let bit = 1 << (fields.len() - index - 1) as u16;
                bit
            } else {
                0
            }
        })
        .sum::<u16>();

    // println!("the gamma rate is {gamma}");
    println!("what");
    let mask = (1u32<<15) - 1;
    println!("result is {}", gamma as u32 * ( !gamma as u32  & mask));
}
