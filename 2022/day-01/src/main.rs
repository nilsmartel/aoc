fn main() {
    let input = std::fs::read_to_string("../input/input-text-01").unwrap();

    let max_calories = input
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
        .max()
        .unwrap();

    println!("{max_calories}");
}
