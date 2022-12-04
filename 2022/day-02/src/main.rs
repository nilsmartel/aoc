fn main() {
    let input = std::fs::read_to_string("../input/input-day-02").unwrap();

    let score: i32 = input
        .lines()
        .map(parse_line)
        .map(|(opp, you)| you.match_score(opp) + you.score())
        .sum();
    println!("{score}");
}

#[derive(Clone, Copy, PartialEq, Eq, PartialOrd, Ord)]
enum Choice {
    Rock,
    Paper,
    Scissor,
}

use Choice::*;

impl Choice {
    fn parse(i: &str) -> Choice {
        match i {
            "A" | "X" => Rock,
            "B" | "Y" => Paper,
            "C" | "Z" => Scissor,
            _ => unreachable!("cant parse '{i}'"),
        }
    }

    fn win(self, other: Choice) -> bool {
        matches!(
            (self, other),
            (Rock, Scissor) | (Paper, Rock) | (Scissor, Paper)
        )
    }

    fn draw(self, other: Choice) -> bool {
        self == other
    }

    fn match_score(self, other: Choice) -> i32 {
        if self.win(other) {
            return 6;
        }

        if self.draw(other) {
            return 3;
        }

        0
    }

    fn score(self) -> i32 {
        match self {
            Rock => 1,
            Paper => 2,
            Scissor => 3,
        }
    }
}

fn parse_line(line: &str) -> (Choice, Choice) {
    let (a, b) = line.split_once(' ').unwrap();
    let b = &b[..1];

    (Choice::parse(a), Choice::parse(b))
}
