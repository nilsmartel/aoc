fn main() {
    let input = std::fs::read_to_string("../input/input-text-01").unwrap();

    // Calories carried per elf
    let mut calories = input
        .split("\n\n")
        // Useful to inspect individual groups
        // .inspect(|g| {
        //     dbg!(g);
        // })
        .map(|group| {
            group
                .split("\n")
                // one empty line occurs at the end
                .filter(|line| !line.is_empty())
                .map(|line| line.parse::<u64>().expect("valid integer string"))
                .sum::<u64>()
        })
        .collect::<Vec<_>>();

    calories.sort_unstable();

    let max = calories[0];
    let top3 = calories[..3].iter().sum::<u64>();

    println!("max:   {max}");
    println!("top 3: {top3}");
}
