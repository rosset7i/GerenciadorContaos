export interface SortingInput {
  field: string;
  direction: Direction
}

export enum Direction {
  ASC = "ASC",
  DESC = "DESC"
}
